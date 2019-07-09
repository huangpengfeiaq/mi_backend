package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:29
 */
@Data
public class ProductInsert {
    /**
     * 商品品类编号
     */
    private Integer categoryId;
    /**
     * 商品封面图
     */
    private String productCover;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品促销语
     */
    private String productPromotion;
    /**
     * 商品简介
     */
    private String productIntroduction;
    /**
     * 商品内容（临时）
     */
    private String productArticleContent;
}
