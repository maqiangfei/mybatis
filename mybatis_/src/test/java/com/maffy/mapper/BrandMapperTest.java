package com.maffy.mapper;

import com.maffy.pojo.Brand;
import com.maffy.util.DbUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/16/2024 5:00 PM
 *
 * MyBatis 参数封装：
 *      * 单个参数：
 *          1. POJO类型：直接使用，属性名 和 参数占位符名称一致
 *          2. Map集合：直接使用，键名 和 参数占位符名称一致
 *          3. Collection: 封装为Map，键名默认为 arg0 | collection
 *              map.put("arg0", 集合)
 *              map.put("collection", 集合)
 *          4. List: 封装为Map，
 *              map.put("arg0", 集合)
 *              map.put("collection", 集合)
 *              map.put("list", 集合)
 *          5. Array: 封装为Map
 *              map.put("arg0", 数组)
 *              map.put("array", 数组)
 *          6. 简单类型：直接使用
 *      * 多个参数：分装为Map集合，可以使用@Param()来替换arg的键名
 *          第一个参数：arg0 | param1
 *          第二个参数：arg1 | param2
 */
public class BrandMapperTest {

    @Test
    public void testFindAll() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.findAll();

        brands.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testFindById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = brandMapper.findById(1);
        System.out.println(brand);

        sqlSession.close();
    }

    @Test
    public void testFindByCondition() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 多个参数时，使用 @Param
        //List<Brand> brands = brandMapper.findByCondition(1, "%华为%", "%华为%");

        // 使用引用类型作为参数
        //Brand brand = new Brand();
        //brand.setStatus(1);
        //brand.setBrandName("%华为%");
        //brand.setCompanyName("%华为%");
        //List<Brand> brands = brandMapper.findByCondition(brand);

        // 使用Map作为参数
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        map.put("companyName", "%华为%");
        map.put("brandName", "%华为%");
        List<Brand> brands = brandMapper.findByCondition(map);

        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testFindByConditionSingle() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = new Brand();
        // brand.setBrandName("华为");
        List<Brand> brands = brandMapper.findByConditionSingle(brand);
        brands.forEach(System.out::println);

        sqlSession.close();
    }

    @Test
    public void testAdd() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = new Brand(null, "波导手机", "波导", 100, "手机中的战斗机", 1);
        System.out.println(brandMapper.add(brand));

        //openSession()没有指定true自动提交事务时，默认是开启事务的，需要手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        Brand brand = new Brand(7, "波导手机2", "波导2", 222, "手机中的战斗机", 1);
        System.out.println(brandMapper.update(brand));

        //openSession()没有指定true自动提交事务时，默认是开启事务的，需要手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        System.out.println(brandMapper.deleteById(7));

        //openSession()没有指定true自动提交事务时，默认是开启事务的，需要手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testDeleteByIds() {
        SqlSession sqlSession = DbUtils.getSqlSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //List<Integer> ids = new ArrayList<>();
        System.out.println(brandMapper.deleteByIds(List.of(8, 9)));

        //openSession()没有指定true自动提交事务时，默认是开启事务的，需要手动提交事务
        sqlSession.commit();

        sqlSession.close();
    }

}
