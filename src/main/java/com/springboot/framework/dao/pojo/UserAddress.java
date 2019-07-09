package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.UserAddressDTO;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:35
 */
@Data
public class UserAddress {
    /**
     * 用户收货地址id
     */
    @Id
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

    public UserAddress() {
    }

    public UserAddress(UserAddressDTO userAddressDTO) {
        this.addressId = userAddressDTO.getAddressId();
        this.userId = userAddressDTO.getUserId();
        this.addressName = userAddressDTO.getAddressName();
        this.addressPhone = userAddressDTO.getAddressPhone();
        this.addressArea = userAddressDTO.getAddressArea();
        this.addressAddress = userAddressDTO.getAddressAddress();
        this.createBy = userAddressDTO.getCreateBy();
        this.createDate = userAddressDTO.getCreateDate();
        this.updateBy = userAddressDTO.getUpdateBy();
        this.updateDate = userAddressDTO.getUpdateDate();
        this.status = userAddressDTO.getStatus();
    }
}