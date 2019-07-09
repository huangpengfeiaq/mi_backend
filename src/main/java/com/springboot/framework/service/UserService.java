package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.User;
import com.springboot.framework.dto.UserDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:44
 */
public interface UserService {
    /**
     * 删除
     *
     * @param userDTO 用户传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(UserDTO userDTO);

    /**
     * 新增
     *
     * @param userDTO 用户传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(UserDTO userDTO);

    /**
     * 登陆
     *
     * @param userDTO 用户传输对象
     * @return ResponseBO<User>
     */
    ResponseBO<User> login(UserDTO userDTO);

    /**
     * 查询
     *
     * @param id 商品主键
     * @return ResponseBO<User>
     */
    ResponseBO<User> selectByPrimaryKey(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    /**
     * 列表查询（根据手机号）
     *
     * @param phone    手机号
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @return ResponseBO<Integer>
     */
    ResponseBO<Integer> selectCount();

    /**
     * 更新
     *
     * @param userDTO 用户传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(UserDTO userDTO);

    /**
     * 更新（根据密码）
     *
     * @param id          用户主键
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @param updateBy    更新人
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
