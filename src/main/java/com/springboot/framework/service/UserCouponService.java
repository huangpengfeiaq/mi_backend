package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.UserCoupon;
import com.springboot.framework.dto.UserCouponDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:26
 */
public interface UserCouponService {
    /**
     * 删除
     *
     * @param userCouponDTO 用户优惠券传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(UserCouponDTO userCouponDTO);

    /**
     * 新增
     *
     * @param userCouponDTO 用户优惠券传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(UserCouponDTO userCouponDTO);

    /**
     * 查询
     *
     * @param id 用户优惠券主键
     * @return ResponseBO<UserCoupon>
     */
    ResponseBO<UserCoupon> selectByPrimaryKey(Integer id);

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
     * @param userCouponDTO 用户优惠券传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(UserCouponDTO userCouponDTO);
}
