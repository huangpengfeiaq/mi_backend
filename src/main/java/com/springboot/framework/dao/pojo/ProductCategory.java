package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.ProductCategoryDTO;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class ProductCategory {
    /**
     * 商品品类编号
     */
    @Id
    private Integer categoryId;
    /**
     * 商品品类名称
     */
    private String categoryName;
    /**
     * 商品品类广告图
     */
    private String categoryCover;
    /**
     * 商品品类序号
     */
    private Integer categorySort;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryDTO productCategoryDTO) {
        this.categoryId = productCategoryDTO.getCategoryId();
        this.categoryName = productCategoryDTO.getCategoryName();
        this.categorySort = productCategoryDTO.getCategorySort();
        this.categoryCover = productCategoryDTO.getCategoryCover();
        this.createBy = productCategoryDTO.getCreateBy();
        this.createDate = productCategoryDTO.getCreateDate();
        this.updateBy = productCategoryDTO.getUpdateBy();
        this.updateDate = productCategoryDTO.getUpdateDate();
        this.status = productCategoryDTO.getStatus();
    }
}