package com.maffy.entity.dto;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maffy.entity.po.User;
import com.maffy.entity.vo.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 9:01 PM
 */
@Data
@ApiModel(description = "分页结果")
public class PageDTO<T> {
    @ApiModelProperty("总条数")
    private Long total;
    @ApiModelProperty("总页数")
    private Long pages;
    @ApiModelProperty("集合")
    private List<T> list;

    public static <PO, VO> PageDTO<VO> of(Page<PO> page, Function<PO, VO> po2vo) {
        PageDTO<VO> dto = new PageDTO<>();
        dto.setTotal(page.getTotal());
        dto.setPages(page.getPages());
        List<PO> records = page.getRecords();
        if(CollUtil.isEmpty(records)) {
            // 结果为空
            dto.setList(Collections.emptyList());
            return dto;
        }
        // 转换records中的PO为VO，设置给dto
        dto.setList(records.stream().map(po2vo).collect(Collectors.toList()));
        // 返回
        return dto;
    }
}
