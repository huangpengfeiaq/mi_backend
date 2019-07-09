package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCategoryDTO {
    /**
     * 商品品类编号
     */
    private Integer categoryId;
    /**
     * 商品品类名称
     */
    private String categoryName;
    /**
     * 商品品类序号
     */
    private Integer categorySort;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public ProductCategoryDTO() {
    }

    /**
     * 新增
     */
    public ProductCategoryDTO(String categoryName, Integer categorySort, String createBy) {
        this.categoryName = categoryName;
        this.categorySort = categorySort;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public ProductCategoryDTO(Integer categoryId, String categoryName, Integer categorySort, String updateBy) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySort = categorySort;
        this.updateBy = updateBy;
    }
}
