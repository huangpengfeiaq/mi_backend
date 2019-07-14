package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class ProductCategoryUpdateByPrimaryKey {
    /**
     * 商品品类id
     */
    private Integer categoryId;
    /**
     * 商品品类名称
     */
    private String categoryName;
    /**
     * 商品品类序号
     */
    private Integer categorySort;
    /**
     * 商品品类广告图
     */
    private String categoryCover;
}
