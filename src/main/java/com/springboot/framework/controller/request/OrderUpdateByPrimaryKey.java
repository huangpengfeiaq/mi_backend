package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:38
 */
@Data
public class OrderUpdateByPrimaryKey {
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户收货地址编号
     */
    private Integer addressId;
    /**
     * 订单总价格
     */
    private BigDecimal orderTotalPrice;
    /**
     * 订单优惠价格
     */
    private BigDecimal orderDiscountsPrice;
    /**
     * 订单运费
     */
    private BigDecimal orderFreightPrice;
    /**
     * 订单实付金额
     */
    private BigDecimal orderPayablePrice;
    /**
     * 物流信息（临时）
     */
    private String orderLogistics;
}
