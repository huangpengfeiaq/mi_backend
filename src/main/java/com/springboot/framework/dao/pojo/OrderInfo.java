package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.OrderInfoDTO;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:12
 */
@Data
public class OrderInfo {
    /**
     * 订单详情编号
     */
    @Id
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

    public OrderInfo() {
    }

    public OrderInfo(OrderInfoDTO orderInfoDTO) {
        this.infoId = orderInfoDTO.getInfoId();
        this.orderId = orderInfoDTO.getOrderId();
        this.styleId = orderInfoDTO.getStyleId();
        this.infoPrice = orderInfoDTO.getInfoPrice();
        this.infoNumber = orderInfoDTO.getInfoNumber();
        this.createBy = orderInfoDTO.getCreateBy();
        this.createDate = orderInfoDTO.getCreateDate();
        this.updateBy = orderInfoDTO.getUpdateBy();
        this.updateDate = orderInfoDTO.getUpdateDate();
        this.status = orderInfoDTO.getStatus();
    }
}