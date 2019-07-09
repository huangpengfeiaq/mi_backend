package com.springboot.framework.controller.request;

import lombok.Data;

@Data
public class ProductUpdateByPrimaryKey {
    //商品编号
    private Integer productId;
    //商品品类编号
    private Integer categoryId;
    //商品封面图
    private String productCover;
    //商品名称
    private String productName;
    //商品促销语
    private String productPromotion;
    //商品简介
    private String productIntroduction;
    //商品内容（临时）
    private String productArticleContent;
}
