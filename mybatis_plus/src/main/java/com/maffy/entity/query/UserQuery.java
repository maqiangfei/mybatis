package com.maffy.entity.query;

import com.maffy.enums.UserStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/19/2024 11:34 AM
 */
@Data
@ApiModel(description = "用户查询条件实体")
public class UserQuery extends PageQuery {

    @ApiModelProperty("用户名关键字")
    private String name;

    @ApiModelProperty("用户状态：1-正常，2-冻结")
    private UserStatus status;

    @ApiModelProperty("余额最小值")
    private Integer minBalance;

    @ApiModelProperty("余额最大值")
    private Integer maxBalance;
}
