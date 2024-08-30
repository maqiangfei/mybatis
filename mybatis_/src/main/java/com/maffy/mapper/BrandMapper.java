package com.maffy.mapper;

import com.maffy.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/16/2024 2:05 PM
 */
public interface BrandMapper {

    List<Brand> findAll();

    // 一个参数时可以不使用@Param
    Brand findById(Integer id);

    // 多个参数时必须使用@Param
    List<Brand> findByCondition(
            @Param("status") int status,
            @Param("companyName") String companyName,
            @Param("brandName") String brandName);

    //传入的引用类型时，会自动找与 #{} 中相同的属性名
    List<Brand> findByCondition(Brand brand);

    List<Brand> findByCondition(Map<String, Object> map);

    //单条件动态查询
    List<Brand> findByConditionSingle(Brand brand);

    //默认返回影响的行数，可以设置返回添加记录的主键
    int add(Brand brand);

    int update(Brand brand);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> ids);

}
