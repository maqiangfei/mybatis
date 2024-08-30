package com.maffy.mapper;

import com.maffy.pojo.Dept;
import com.maffy.pojo.Emp;
import com.maffy.util.DbUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/21/2024 9:32 PM
 */
public class ResultMapTest {

    /**
     * association 处理多对一关系（表与表之间多对一的关系）
     */
    @Test
    public void testGetEmpWithDeptById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpWithDeptById(1);
        System.out.println(emp);
    }

    /**
     * association 实现分步查询/延迟加载
     */
    @Test
    public void testGetEmpById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = empMapper.getEmpById(1);
        System.out.println(emp);
    }

    /**
     * collection 处理一对多关系（表与表之间一对多的关系）
     */
    @Test
    public void testGetDeptWithEmpById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptWithEmpById(1);
        System.out.println(dept);
    }

    /**
     * collection 实现分步查询/延迟加载
     */
    @Test
    public void testGetDeptById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = deptMapper.getDeptById(1);
        System.out.println(dept);
    }
}
