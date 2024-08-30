package com.maffy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.maffy.entity.dto.PageDTO;
import com.maffy.entity.po.Address;
import com.maffy.entity.query.UserQuery;
import com.maffy.entity.vo.AddressVO;
import com.maffy.entity.vo.UserVO;
import com.maffy.enums.UserStatus;
import com.maffy.mapper.UserMapper;
import com.maffy.entity.po.User;
import com.maffy.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author 马强飞
 * @version 1.0
 * @since 7/18/2024 5:23 PM
 *
 * 不需要注入 UserMapper, 直接使用 baseMapper
 * 继承了ServiceImpl, 实现了IService，它们的方法可以直接使用
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * @param id
     * @param money
     */
    @Override
    @Transactional
    public void decrBalanceById(Long id, Integer money) {
        // 1.查询用户
        User user = getById(id);
        // 2.校验用户状态
        if (user == null || user.getStatus() == UserStatus.FROZEN) {
            throw new RuntimeException("用户状态异常！");
        }
        // 3.校检余额是否充足
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足！");
        }
        // 4.扣减余额
        int remainBalance = user.getBalance() - money;  //并发问题：同时拿到同一个用户，扣减后金额相同，导致只扣减了一次
        boolean flag = lambdaUpdate()
                .set(User::getBalance, remainBalance)
                .set(remainBalance == 0, User::getStatus, 2)
                .eq(User::getId, id)
                .eq(User::getBalance, user.getBalance())  //乐观锁，在数据库扣减时，判断是否被修改过
                .update();
        if (!flag) {
            throw new RuntimeException("扣除失败，请重试！");
        }
    }

    @Override
    public List<User> queryUserByCondition(String name, UserStatus status, Integer minBalance, Integer maxBalance) {
        return lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .gt(minBalance != null, User::getBalance, minBalance)
                .lt(maxBalance != null, User::getBalance, maxBalance)
                .list();
    }

    @Override
    public UserVO queryUserWithAddressById(Long id) {
        // 1.查询用户
        User user = getById(id);
        if (user == null || user.getStatus() == UserStatus.FROZEN) {
            throw new RuntimeException("用户状态异常！");
        }
        // 2.查询地址
        List<Address> addresses = Db.lambdaQuery(Address.class).eq(Address::getUserId, id).list();
        // 3.封装VO
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if (CollUtil.isNotEmpty(addresses)) {
            userVO.setAddresses(BeanUtil.copyToList(addresses, AddressVO.class));
        }
        return userVO;
    }

    @Override
    public List<UserVO> queryUserWithAddressByIds(List<Long> ids) {
        // 查询用户
        List<User> users = listByIds(ids);
        if (CollUtil.isEmpty(users)) {
             return Collections.emptyList();
        }
        // 查询地址
        List<Long> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        List<Address> addresses = Db.lambdaQuery(Address.class).in(Address::getUserId, userIds).list();
        Map<Long, List<AddressVO>> addressMap = new HashMap<>();
        if (CollUtil.isNotEmpty(addresses)) {
            // 转换地址VO
            List<AddressVO> addressVOList = BeanUtil.copyToList(addresses, AddressVO.class);
            // 对地址VO进行分组
            addressMap = addressVOList.stream().collect(Collectors.groupingBy(AddressVO::getUserId));
        }
        List<UserVO> userVOList = new ArrayList<>(users.size());
        for (User user : users) {
            // 转换用户VO
            UserVO vo = BeanUtil.copyProperties(user, UserVO.class);
            userVOList.add(vo);
            // 设置地址VO
            vo.setAddresses(addressMap.get(user.getId()));
        }
        return userVOList;
    }

    @Override
    public PageDTO<UserVO> queryUserPages(UserQuery query) {
        String name = query.getName();
        UserStatus status = query.getStatus();
        // 构建分页条件
        Page<User> page = query.toMpPageDefaultSortByUpdateTime();

        // 分页查询
        Page<User> userPage = lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .page(page);

        // 封装VO结果返回
        // return PageDTO.of(userPage, user -> BeanUtil.copyProperties(user, UserVO.class));
        return PageDTO.of(userPage, user -> {
            // vo2po
            // 拷贝基础属性
            UserVO vo = BeanUtil.copyProperties(user, UserVO.class);
            // 处理特殊逻辑 egs: PO与VO的属性名不一样，hutool无法copy的。 或将PO的属性做个处理再给VO
            // 下面的例子是将用户名后两位隐藏
            vo.setUsername(vo.getUsername().substring(0, vo.getUsername().length() - 2) + "**");
            return vo;
        });
    }
}
