package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.Order;
import com.springboot.framework.dto.OrderDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:22
 */
public interface OrderService extends BaseService<Order, OrderDTO> {
}
