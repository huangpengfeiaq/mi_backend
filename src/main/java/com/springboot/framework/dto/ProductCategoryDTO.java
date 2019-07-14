package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategoryDTO {
    /**
     * 商品品类编号
     */
    private Integer categoryId;
    /**
     * 商品品类名称
     */
    private String categoryName;
    /**
     * 商品品类广告图
     */
    private String categoryCover;
    /**
     * 商品品类序号
     */
    private Integer categorySort;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
}
