package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品品类编号
     */
    private Integer categoryId;
    /**
     * 商品品类名称
     */
    private String categoryName;
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    /**
     * 商品内容（临时）
     */
    private String productArticleContent;

    public ProductDTO() {
    }

    /**
     * 删除
     */
    public ProductDTO(Integer productId, String updateBy) {
        this.productId = productId;
        this.updateBy = updateBy;
    }

    /**
     * 新增
     */
    public ProductDTO(Integer categoryId, String productCover, String productName, String productPromotion, String productIntroduction, String productArticleContent, String createBy) {
        this.categoryId = categoryId;
        this.productCover = productCover;
        this.productName = productName;
        this.productPromotion = productPromotion;
        this.productIntroduction = productIntroduction;
        this.productArticleContent = productArticleContent;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public ProductDTO(Integer productId, Integer categoryId, String productCover, String productName, String productPromotion, String productIntroduction, String productArticleContent, String updateBy) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productCover = productCover;
        this.productName = productName;
        this.productPromotion = productPromotion;
        this.productIntroduction = productIntroduction;
        this.updateBy = updateBy;
        this.productArticleContent = productArticleContent;
    }
}
