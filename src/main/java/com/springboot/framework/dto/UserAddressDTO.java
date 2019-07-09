package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:48
 */
@Data
public class UserAddressDTO {
    /**
     * 用户收货地址id
     */
    private Integer addressId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户收货地址收货人
     */
    private String addressName;
    /**
     * 用户收货地址手机号
     */
    private String addressPhone;
    /**
     * 用户收货地址区域
     */
    private String addressArea;
    /**
     * 用户收货地址详细地址
     */
    private String addressAddress;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    public UserAddressDTO() {
    }

    //新增
    public UserAddressDTO(Integer userId, String addressName, String addressPhone, String addressArea, String addressAddress, String createBy) {
        this.userId = userId;
        this.addressName = addressName;
        this.addressPhone = addressPhone;
        this.addressArea = addressArea;
        this.addressAddress = addressAddress;
        this.createBy = createBy;
    }

    //更新
    public UserAddressDTO(Integer addressId, Integer userId, String addressName, String addressPhone, String addressArea, String addressAddress, String updateBy) {
        this.addressId = addressId;
        this.userId = userId;
        this.addressName = addressName;
        this.addressPhone = addressPhone;
        this.addressArea = addressArea;
        this.addressAddress = addressAddress;
        this.updateBy = updateBy;
    }
}
