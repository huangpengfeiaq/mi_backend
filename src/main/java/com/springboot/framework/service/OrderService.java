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
public interface OrderService {
    /**
     * 删除
     *
     * @param orderDTO 订单传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(OrderDTO orderDTO);

    /**
     * 新增
     *
     * @param orderDTO 订单传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(OrderDTO orderDTO);

    /**
     * 查询
     *
     * @param id 订单主键
     * @return ResponseBO<Order>
     */
    ResponseBO<Order> selectByPrimaryKey(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @return ResponseBO<Integer>
     */
    ResponseBO<Integer> selectCount();

    /**
     * 更新
     *
     * @param orderDTO 订单传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(OrderDTO orderDTO);
}
