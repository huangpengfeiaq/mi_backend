package com.springboot.framework.controller.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:31
 */
@Data
public class UserCouponUpdateByPrimaryKey {
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
}
