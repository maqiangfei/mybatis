package com.maffy.entity.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maffy.entity.po.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 8:20 PM
 *
 * 所有需要分页查询的请求都可以继承它
 */
@Data
@ApiModel(description = "分页查询实体")
public class PageQuery {

    @ApiModelProperty("页码")
    private Integer pageNo = 1;

    @ApiModelProperty("页大小")
    private Integer pageSize = 5;

    @ApiModelProperty("排序字段")
    private String sortBy;

    @ApiModelProperty("是否升序")
    private boolean isAsc = true;

    public <T> Page<T> toMpPage(OrderItem... defaultItems) {
        // 构建分页条件
        Page<T> page = Page.of(pageNo, pageSize);
        // 排序条件
        if (StrUtil.isNotBlank(sortBy)) {
            // 不为空
            page.addOrder(new OrderItem().setColumn(sortBy).setAsc(isAsc));
        } else if (defaultItems != null){
            //为空，默认排序
            page.addOrder(defaultItems);
        }
        return page;
    }

    public <T> Page<T> toMpPage(String defaultSortBy, Boolean isAsc) {
        return toMpPage(new OrderItem().setColumn(defaultSortBy).setAsc(isAsc));
    }
    public <T> Page<T> toMpPageDefaultSortByCreateTime() {
        return toMpPage(new OrderItem().setColumn("create_time").setAsc(false));
    }
    public <T> Page<T> toMpPageDefaultSortByUpdateTime() {
        return toMpPage(new OrderItem().setColumn("update_time").setAsc(false));
    }
}
