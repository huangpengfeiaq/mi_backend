package com.springboot.framework.vo;

import com.springboot.framework.dao.pojo.ProductStyle;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/11 22:09
 */
@Data
public class ProductStyleVO {
    /**
     * 商品款式编号
     */
    private Integer styleId;
    /**
     * 商品编号
     */
    private Integer productId;
    /**
     * 商品名称（附加）
     */
    private String productName;
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

    public ProductStyleVO() {
    }

    public ProductStyleVO(ProductStyle productStyle, String productName) {
        this.styleId = productStyle.getStyleId();
        this.productId = productStyle.getProductId();
        this.productName = productName;
        this.styleCover = productStyle.getStyleCover();
        this.stylePromotionalPrice = productStyle.getStylePromotionalPrice();
        this.stylePrice = productStyle.getStylePrice();
        this.styleVersion = productStyle.getStyleVersion();
        this.styleColor = productStyle.getStyleColor();
        this.createBy = productStyle.getCreateBy();
        this.createDate = productStyle.getCreateDate();
        this.updateBy = productStyle.getUpdateBy();
        this.updateDate = productStyle.getUpdateDate();
        this.status = productStyle.getStatus();
    }
}
