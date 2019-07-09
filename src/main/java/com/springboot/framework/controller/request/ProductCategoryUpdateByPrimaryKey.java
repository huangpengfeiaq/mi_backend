package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class ProductCategoryUpdateByPrimaryKey {
    //商品品类编号
    private Integer categoryId;
    //商品品类名称
    private String categoryName;
    //商品品类序号
    private Integer categorySort;
}
