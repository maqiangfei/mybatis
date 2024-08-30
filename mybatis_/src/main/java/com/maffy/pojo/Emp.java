package com.maffy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/21/2024 9:49 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {

    private Integer id;

    private String username;

    private Integer age;

    private String gender;

    private Integer deptId;

    private Dept dept;
}
