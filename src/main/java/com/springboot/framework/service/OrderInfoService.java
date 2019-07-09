package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.OrderInfo;
import com.springboot.framework.dto.OrderInfoDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/9 12:27
 */
public interface OrderInfoService {
    /**
     * 删除
     *
     * @param orderInfoDTO 订单详情传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(OrderInfoDTO orderInfoDTO);

    /**
     * 新增
     *
     * @param orderInfoDTO 订单详情传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(OrderInfoDTO orderInfoDTO);

    /**
     * 查询
     *
     * @param id 订单详情主键
     * @return ResponseBO<OrderInfo>
     */
    ResponseBO<OrderInfo> selectByPrimaryKey(Integer id);

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
     * @param orderInfoDTO 订单详情传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(OrderInfoDTO orderInfoDTO);
}
