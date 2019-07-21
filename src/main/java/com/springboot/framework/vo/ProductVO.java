package com.springboot.framework.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/10 11:16
 */
@Data
public class ProductVO {
    /**
     * 商品编号
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private BigDecimal price;
    /**
     * 商品简介
     */
    private String productIntroduction;
    /**
     * 商品内容（临时）
     */
    private String productArticleContent;
}
