package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.ProductDTO;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "product_master")
public class Product {
    //商品编号
    @Id
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    //商品内容（临时）
    private String productArticleContent;

    public String getProductArticleContent() {
        return productArticleContent;
    }

    public void setProductArticleContent(String productArticleContent) {
        this.productArticleContent = productArticleContent == null ? null : productArticleContent.trim();
    }

    public Product() {
    }

    public Product(ProductDTO productDTO) {
        this.productId = productDTO.getProductId();
        this.categoryId = productDTO.getCategoryId();
        this.productCover = productDTO.getProductCover();
        this.productName = productDTO.getProductName();
        this.productPromotion = productDTO.getProductPromotion();
        this.productIntroduction = productDTO.getProductIntroduction();
        this.createBy = productDTO.getCreateBy();
        this.createDate = productDTO.getCreateDate();
        this.updateBy = productDTO.getUpdateBy();
        this.updateDate = productDTO.getUpdateDate();
        this.status = productDTO.getStatus();
        this.productArticleContent = productDTO.getProductArticleContent();
    }
}