package com.maffy.mapper;

import com.maffy.pojo.Emp;

import java.util.List;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/21/2024 9:55 PM
 */
public interface EmpMapper {

    List<Emp> getEmpsByDeptId(Integer id);

    Emp getEmpWithDeptById(Integer id);

    /**
     * 通过分布查询员工及员工对应部门信息
     * 分布查询第二部：通过dept_id查询员工对应部门
     * @param id
     * @return
     */
    Emp getEmpById(Integer id);
}
