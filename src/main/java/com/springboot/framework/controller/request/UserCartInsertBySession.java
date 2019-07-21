package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/22 0:36
 */
@Data
public class UserCartInsertBySession {
    /**
     * 商品款式id
     */
    private Integer styleId;
    /**
     * 用户购物车商品数量
     */
    private Integer cartNumber;
}
