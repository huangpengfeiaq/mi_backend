package com.springboot.framework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:21
 */
@Data
public class OrderDTO {
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;
    /**
     * 物流信息（临时）
     */
    private String orderLogistics;

    public OrderDTO() {
    }

    /**
     * 新增
     */
    public OrderDTO(Integer userId, Integer addressId, BigDecimal orderTotalPrice, BigDecimal orderDiscountsPrice, BigDecimal orderFreightPrice, BigDecimal orderPayablePrice, String orderLogistics, String createBy) {
        this.userId = userId;
        this.addressId = addressId;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDiscountsPrice = orderDiscountsPrice;
        this.orderFreightPrice = orderFreightPrice;
        this.orderPayablePrice = orderPayablePrice;
        this.orderLogistics = orderLogistics;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public OrderDTO(Integer orderId, Integer userId, Integer addressId, BigDecimal orderTotalPrice, BigDecimal orderDiscountsPrice, BigDecimal orderFreightPrice, BigDecimal orderPayablePrice, String orderLogistics, String updateBy) {
        this.orderId = orderId;
        this.userId = userId;
        this.addressId = addressId;
        this.orderTotalPrice = orderTotalPrice;
        this.orderDiscountsPrice = orderDiscountsPrice;
        this.orderFreightPrice = orderFreightPrice;
        this.orderPayablePrice = orderPayablePrice;
        this.orderLogistics = orderLogistics;
        this.updateBy = updateBy;
    }
}
