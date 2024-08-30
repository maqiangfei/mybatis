package com.maffy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maffy.entity.dto.PageDTO;
import com.maffy.entity.po.User;
import com.maffy.entity.query.UserQuery;
import com.maffy.entity.vo.UserVO;
import com.maffy.enums.UserStatus;

import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/18/2024 5:23 PM
 */
public interface IUserService extends IService<User>{

    void decrBalanceById(Long id, Integer money);

    List<User> queryUserByCondition(String name, UserStatus status, Integer minBalance, Integer maxBalance);

    UserVO queryUserWithAddressById(Long id);

    List<UserVO> queryUserWithAddressByIds(List<Long> ids);

    PageDTO<UserVO> queryUserPages(UserQuery query);
}
