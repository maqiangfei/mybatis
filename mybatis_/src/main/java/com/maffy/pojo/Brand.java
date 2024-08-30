package com.maffy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 马强飞
 * @version 1.0
 * @since 7/16/2024 2:57 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private Integer id;

    private String brandName;

    private String companyName;

    private Integer ordered;

    private String description;

    private Integer status;
}
