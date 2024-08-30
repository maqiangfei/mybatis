package com.maffy.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maffy.App;
import com.maffy.entity.po.User;
import com.maffy.entity.po.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/17/2024 7:30 PM
 */
@SpringBootTest
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 插入
     */
    @Test
    void testInsert() {
        User user = new User();
        // user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("19216914913");
        user.setBalance(200);
        // user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setInfo(UserInfo.of(24, "英文老师", "female"));
        userMapper.insert(user);
    }

    /**
     * 根据 id 查询
     */
    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println(user);
    }

    /**
     * 根据 id集合 查询
     */
    @Test
    void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    /**
     * 根据 id 更新
     */
    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(6L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    /**
     * 根据 id 删除
     */
    @Test
    void testDeleteById() {
        userMapper.deleteById(6L);
    }

    /**
     * 查询： 名字中有“o”，存款大于 1000 的人 的 id, username, info, balance
     */
    @Test
    void testQueryWrapper() {
        Wrapper<User> wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 2000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 更新： 名字叫 Jack 的存款为 2000
     */
    @Test
    void testUpdateByQueryWrapper() {
        // 1. 要更新的数据
        User user = new User();
        user.setBalance(2000);
        // 2. 更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", "jack");
        // 3. 执行更新
        userMapper.update(user, wrapper);
    }

    /**
     * 更新： id 为 1， 2， 4 的用户的余额， 扣200
     */
    @Test
    void testUpdateByUpdateWrapper() {
        List<Long> ids = List.of(1L, 2L, 4L);
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance + 200")
                .in("id", ids);
        userMapper.update(null, wrapper);
    }

    /**
     * 使用Lambda方式实现
     * 查询：名字中有“o”，存款大于 1000 的人 的 id, username, info, balance
     */
    @Test
    void testLambdaQueryWrapper() {
        Wrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    /**
     * 使用自定义sql
     * 根据多个 id 减 存款 指定值
     */
    @Test
    void testUpdateBalanceByIds() {
        // 条件
        List<Long> ids = List.of(1L, 2L, 4L);
        int amount = 200;
        // 构建条件
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .in(User::getId, ids);
        // 将条件传入 mapper层
        userMapper.updateBalanceByIds(wrapper, amount);
    }

}
