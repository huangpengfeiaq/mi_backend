package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;

/**
 * @param <POJO> 对应的POJO类
 * @param <DTO>  对应的DTO类
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/15 20:55
 */
public interface BaseService<POJO, DTO> {
    /**
     * 删除
     *
     * @param tDTO 数据传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(DTO tDTO);

    /**
     * 新增
     *
     * @param tDTO 数据传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(DTO tDTO);

    /**
     * 查询
     *
     * @param primaryKey 主键
     * @return ResponseBO<Order>
     */
    ResponseBO<POJO> selectByPrimaryKey(Integer primaryKey);

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
     * @param tDTO 数据传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(DTO tDTO);
}
