package com.springboot.framework.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:23
 */
@Data
public class UserCouponDTO {
    /**
     * 用户优惠券id
     */
    private Integer couponId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户优惠券面值
     */
    private BigDecimal couponPrice;
    /**
     * 用户优惠券名称
     */
    private String couponName;
    /**
     * 用户优惠券满减金额
     */
    private BigDecimal couponConditionPrice;
    /**
     * 用户优惠券有效期
     */
    private Date couponPeriodOfValidity;
    /**
     * 用户优惠券详细内容
     */
    private String couponCondition;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public UserCouponDTO() {
    }

    /**
     * 新增
     */
    public UserCouponDTO(Integer userId, BigDecimal couponPrice, String couponName, BigDecimal couponConditionPrice, Date couponPeriodOfValidity, String couponCondition, String createBy) {
        this.userId = userId;
        this.couponPrice = couponPrice;
        this.couponName = couponName;
        this.couponConditionPrice = couponConditionPrice;
        this.couponPeriodOfValidity = couponPeriodOfValidity;
        this.couponCondition = couponCondition;
        this.createBy = createBy;
    }

    /**
     * 更新
     */
    public UserCouponDTO(Integer couponId, Integer userId, BigDecimal couponPrice, String couponName, BigDecimal couponConditionPrice, Date couponPeriodOfValidity, String couponCondition, String updateBy) {
        this.couponId = couponId;
        this.userId = userId;
        this.couponPrice = couponPrice;
        this.couponName = couponName;
        this.couponConditionPrice = couponConditionPrice;
        this.couponPeriodOfValidity = couponPeriodOfValidity;
        this.couponCondition = couponCondition;
        this.updateBy = updateBy;
    }
}
