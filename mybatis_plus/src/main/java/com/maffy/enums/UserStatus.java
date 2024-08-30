package com.maffy.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 4:09 PM
 */
@Getter
public enum UserStatus {
    NORMAL(1, "正常"),
    FROZEN(2, "冻结"),
    ;
    @EnumValue //告诉类型处理器：enum中的哪个属性和表字段映射
    private final int value;
    @JsonValue  //加在什么上返回的就是什么值
    private final String desc;

    UserStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    //根据前端传入的int类型 status，返回对应的枚举类型 UserStatus;
    @JsonCreator
    public static UserStatus getByValue(Integer status) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (userStatus.value == status) {
                return userStatus;
            }
        }
        return null;
    }
}
