package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:59
 */
@Data
public class UserCartDTO {
    /**
     * 用户购物车id
     */
    private Integer cartId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商品款式id
     */
    private Integer styleId;
    /**
     * 用户购物车商品数量
     */
    private Integer cartNumber;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public UserCartDTO() {
    }

    //新增
    public UserCartDTO(Integer userId, Integer styleId, Integer cartNumber, String createBy) {
        this.userId = userId;
        this.styleId = styleId;
        this.cartNumber = cartNumber;
        this.createBy = createBy;
    }

    //更新
    public UserCartDTO(Integer cartId, Integer userId, Integer styleId, Integer cartNumber, String updateBy) {
        this.cartId = cartId;
        this.userId = userId;
        this.styleId = styleId;
        this.cartNumber = cartNumber;
        this.updateBy = updateBy;
    }
}
