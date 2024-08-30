package com.maffy.mapper;

import com.maffy.pojo.Dept;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/21/2024 10:35 PM
 */
public interface DeptMapper {

    Dept getDeptById(Integer id);

    Dept getDeptWithEmpById(Integer id);
}
