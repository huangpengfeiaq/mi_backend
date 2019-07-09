package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.UserAddress;
import com.springboot.framework.dto.UserAddressDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:50
 */
public interface UserAddressService {
    /**
     * 删除
     *
     * @param userAddressDTO 用户收货地址传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(UserAddressDTO userAddressDTO);

    /**
     * 新增
     *
     * @param userAddressDTO 用户收货地址传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(UserAddressDTO userAddressDTO);

    /**
     * 查询
     *
     * @param id 用户收货地址主键
     * @return ResponseBO<UserAddress>
     */
    ResponseBO<UserAddress> selectByPrimaryKey(Integer id);

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
     * @param userAddressDTO 用户收货地址传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(UserAddressDTO userAddressDTO);
}
