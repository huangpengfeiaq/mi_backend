package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.UserCart;
import com.springboot.framework.dto.UserCartDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:01
 */
public interface UserCartService {
    /**
     * 删除
     *
     * @param userCartDTO 用户购物车传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(UserCartDTO userCartDTO);

    /**
     * 新增
     *
     * @param userCartDTO 用户购物车传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(UserCartDTO userCartDTO);

    /**
     * 查询
     *
     * @param id 用户购物车主键
     * @return ResponseBO<UserCart>
     */
    ResponseBO<UserCart> selectByPrimaryKey(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    /**
     * 列表查询(根据用户id)
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @param userId 用户id
     * @return PageResponseBO
     */
    PageResponseBO selectListByUserId(Integer userId,Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @return ResponseBO<Integer>
     */
    ResponseBO<Integer> selectCount();

    /**
     * 更新
     *
     * @param userCartDTO 用户购物车传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(UserCartDTO userCartDTO);

    /**
     * 更新(数量)
     *
     * @param userCartDTO 用户购物车传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateBySession(UserCartDTO userCartDTO);
}
