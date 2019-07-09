package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.OrderDTO;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:12
 */
@Data
@Table(name = "order_master")
public class Order {
    /**
     * 订单编号
     */
    @Id
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

    public String getOrderLogistics() {
        return orderLogistics;
    }

    public void setOrderLogistics(String orderLogistics) {
        this.orderLogistics = orderLogistics == null ? null : orderLogistics.trim();
    }

    public Order() {
    }

    public Order(OrderDTO orderDTO) {
        this.orderId = orderDTO.getOrderId();
        this.userId = orderDTO.getUserId();
        this.addressId = orderDTO.getAddressId();
        this.orderTotalPrice = orderDTO.getOrderTotalPrice();
        this.orderDiscountsPrice = orderDTO.getOrderDiscountsPrice();
        this.orderFreightPrice = orderDTO.getOrderFreightPrice();
        this.orderPayablePrice = orderDTO.getOrderPayablePrice();
        this.createBy = orderDTO.getCreateBy();
        this.createDate = orderDTO.getCreateDate();
        this.updateBy = orderDTO.getUpdateBy();
        this.updateDate = orderDTO.getUpdateDate();
        this.status = orderDTO.getStatus();
        this.orderLogistics = orderDTO.getOrderLogistics();
    }
}