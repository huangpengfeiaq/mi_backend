package com.springboot.framework.vo;

import com.springboot.framework.dao.pojo.ProductStyle;
import com.springboot.framework.dao.pojo.UserCart;
import com.springboot.framework.util.StringUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/22 0:04
 */
@Data
public class UserCartVO {
    /**
     * productId
     */
    private Integer productId;
    /**
     * 用户购物车id
     */
    private Integer id;
    /**
     * 商品款式封面图
     */
    private String img;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品款式规格
     */
    private String spec;
    /**
     * 商品款式活动价格
     */
    private BigDecimal price;
    /**
     * 用户购物车商品数量
     */
    private Integer number;
    /**
     * 选中状态
     */
    private Boolean selected;

    public UserCartVO() {
    }

    public UserCartVO(UserCart userCart, ProductStyle productStyle, String name) {
        this.productId = productStyle.getProductId();
        this.id = userCart.getCartId();
        this.img = productStyle.getStyleCover();
        this.name = name;
        this.spec = productStyle.getStyleVersion() + productStyle.getStyleColor();
        this.price = StringUtil.isBlank(productStyle.getStylePromotionalPrice()) ? productStyle.getStylePrice() : productStyle.getStylePromotionalPrice();
        this.number = userCart.getCartNumber();
        //临时统一设置为false（即未选中）
        this.selected = false;
    }
}
