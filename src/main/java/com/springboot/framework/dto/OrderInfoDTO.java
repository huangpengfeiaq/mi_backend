package com.springboot.framework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:19
 */
@Data
public class OrderInfoDTO {
    /**
     * 订单详情编号
     */
    private Integer infoId;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 商品款式编号
     */
    private Integer styleId;
    /**
     * 订单详情价格
     */
    private BigDecimal infoPrice;
    /**
     * 订单详情数量
     */
    private Integer infoNumber;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public OrderInfoDTO() {
    }

    /**
     * 新增
     */
    public OrderInfoDTO(Integer orderId, Integer styleId, BigDecimal infoPrice, Integer infoNumber, String createBy) {
        this.orderId = orderId;
        this.styleId = styleId;
        this.infoPrice = infoPrice;
        this.infoNumber = infoNumber;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public OrderInfoDTO(Integer infoId, Integer orderId, Integer styleId, BigDecimal infoPrice, Integer infoNumber, String updateBy) {
        this.infoId = infoId;
        this.orderId = orderId;
        this.styleId = styleId;
        this.infoPrice = infoPrice;
        this.infoNumber = infoNumber;
        this.updateBy = updateBy;
    }
}
