package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.UserCouponDTO;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:35
 */
@Data
public class UserCoupon {
    /**
     * 用户优惠券id
     */
    @Id
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

    public UserCoupon() {
    }

    public UserCoupon(UserCouponDTO userCouponDTO) {
        this.couponId = userCouponDTO.getCouponId();
        this.userId = userCouponDTO.getUserId();
        this.couponPrice = userCouponDTO.getCouponPrice();
        this.couponName = userCouponDTO.getCouponName();
        this.couponConditionPrice = userCouponDTO.getCouponConditionPrice();
        this.couponPeriodOfValidity = userCouponDTO.getCouponPeriodOfValidity();
        this.couponCondition = userCouponDTO.getCouponCondition();
        this.createBy = userCouponDTO.getCreateBy();
        this.createDate = userCouponDTO.getCreateDate();
        this.updateBy = userCouponDTO.getUpdateBy();
        this.updateDate = userCouponDTO.getUpdateDate();
        this.status = userCouponDTO.getStatus();
    }
}