package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:22
 */
@Data
public class ProductStyleUpdateByPrimaryKey {
    /**
     * 商品款式编号
     */
    private Integer styleId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品款式封面图
     */
    private String styleCover;
    /**
     * 商品款式活动价格
     */
    private BigDecimal stylePromotionalPrice;
    /**
     * 商品款式原价
     */
    private BigDecimal stylePrice;
    /**
     * 商品款式版本
     */
    private String styleVersion;
    /**
     * 商品款式颜色
     */
    private String styleColor;
}
