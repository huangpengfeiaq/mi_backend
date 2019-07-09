package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.UserCartDTO;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:35
 */
@Data
public class UserCart {
    /**
     * 用户购物车id
     */
    @Id
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

    public UserCart() {
    }

    public UserCart(UserCartDTO userCartDTO) {
        this.cartId = userCartDTO.getCartId();
        this.userId = userCartDTO.getUserId();
        this.styleId = userCartDTO.getStyleId();
        this.cartNumber = userCartDTO.getCartNumber();
        this.createBy = userCartDTO.getCreateBy();
        this.createDate = userCartDTO.getCreateDate();
        this.updateBy = userCartDTO.getUpdateBy();
        this.updateDate = userCartDTO.getUpdateDate();
        this.status = userCartDTO.getStatus();
    }
}