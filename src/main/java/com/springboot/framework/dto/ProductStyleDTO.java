package com.springboot.framework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:10
 */
@Data
public class ProductStyleDTO {
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public ProductStyleDTO() {
    }

    /**
     * 新增
     */
    public ProductStyleDTO(Integer productId, String styleCover, BigDecimal stylePromotionalPrice, BigDecimal stylePrice, String styleVersion, String styleColor, String createBy) {
        this.productId = productId;
        this.styleCover = styleCover;
        this.stylePromotionalPrice = stylePromotionalPrice;
        this.stylePrice = stylePrice;
        this.styleVersion = styleVersion;
        this.styleColor = styleColor;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public ProductStyleDTO(Integer styleId, Integer productId, String styleCover, BigDecimal stylePromotionalPrice, BigDecimal stylePrice, String styleVersion, String styleColor, String updateBy) {
        this.styleId = styleId;
        this.productId = productId;
        this.styleCover = styleCover;
        this.stylePromotionalPrice = stylePromotionalPrice;
        this.stylePrice = stylePrice;
        this.styleVersion = styleVersion;
        this.styleColor = styleColor;
        this.updateBy = updateBy;
    }
}
