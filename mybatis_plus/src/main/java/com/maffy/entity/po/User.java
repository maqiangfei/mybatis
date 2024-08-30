package com.maffy.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.maffy.enums.UserStatus;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/16/2024 1:06 PM
 */
@Data
@TableName(value = "tb_user", autoResultMap = true) //对象中嵌套对象，则要开启自动结果集映射
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //不指定默认是 ASSIGN_ID 雪花算法, 可以在配置中修改
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    @TableField(typeHandler = JacksonTypeHandler.class) //对象与数据库中的json类型数据映射
    private UserInfo info;

    private String password;

    private String phone;

    private UserStatus status;

    private Integer balance;

    //默认会驼峰转下划线 --> create_time
    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
