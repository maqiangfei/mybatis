package com.maffy.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 5:04 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")  //静态全参数构造
public class UserInfo {
    private Integer age;
    private String intro;
    private String gender;
}
