package com.maffy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/21/2024 9:53 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {

    private Integer id;

    private String name;

    private List<Emp> emps;
}
