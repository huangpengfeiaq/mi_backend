package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.ProductStyleDTO;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductStyle {
    /**
     * 商品款式编号
     */
    @Id
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

    public ProductStyle() {
    }

    public ProductStyle(ProductStyleDTO productStyleDTO) {
        this.styleId = productStyleDTO.getStyleId();
        this.productId = productStyleDTO.getProductId();
        this.styleCover = productStyleDTO.getStyleCover();
        this.stylePromotionalPrice = productStyleDTO.getStylePromotionalPrice();
        this.stylePrice = productStyleDTO.getStylePrice();
        this.styleVersion = productStyleDTO.getStyleVersion();
        this.styleColor = productStyleDTO.getStyleColor();
        this.createBy = productStyleDTO.getCreateBy();
        this.createDate = productStyleDTO.getCreateDate();
        this.updateBy = productStyleDTO.getUpdateBy();
        this.updateDate = productStyleDTO.getUpdateDate();
        this.status = productStyleDTO.getStatus();
    }
}