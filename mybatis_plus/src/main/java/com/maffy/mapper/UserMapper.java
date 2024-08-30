package com.maffy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maffy.entity.po.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author 马强飞
 * @version 1.0
 * @since 7/17/2024 6:21 PM
 */
public interface UserMapper extends BaseMapper<User> {

    int updateBalanceByIds(
            @Param("ew") LambdaQueryWrapper<User> wrapper,
            @Param("money") int money);
}
