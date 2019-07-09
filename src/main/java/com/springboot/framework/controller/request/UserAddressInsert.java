package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:56
 */
@Data
public class UserAddressInsert {
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
}
