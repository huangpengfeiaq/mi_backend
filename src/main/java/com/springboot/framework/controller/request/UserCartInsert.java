package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:06
 */
@Data
public class UserCartInsert {
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
}
