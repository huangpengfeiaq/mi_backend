package com.springboot.framework.controller.request;

import lombok.Data;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/22 11:03
 */
@Data
public class UserCartUpdateBySession {
    /**
     * cartId
     */
    private Integer cartId;
    /**
     * 增加或减少（1-减少，2-增加）
     */
    private Integer type;
}
