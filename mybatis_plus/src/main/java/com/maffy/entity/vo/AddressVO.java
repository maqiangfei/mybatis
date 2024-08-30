package com.maffy.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/20/2024 10:50 AM
 */
@Data
@ApiModel(description = "收货地址VO")
public class AddressVO {

    @ApiModelProperty("id")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("县/区")
    private String town;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("街道")
    private String street;

    @ApiModelProperty("联系方式")
    private String contact;
}
