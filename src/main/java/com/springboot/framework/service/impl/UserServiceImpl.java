package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.UserMapper;
import com.springboot.framework.dao.pojo.User;
import com.springboot.framework.dto.UserDTO;
import com.springboot.framework.service.UserService;
import com.springboot.framework.util.MD5Util;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.util.StringUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:49
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(UserDTO recordDTO) {
        //2.创建entity
        User record = new User(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (userMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(UserDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "insertSelective");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        User record = new User(recordDTO);
        record.setUserPassword(MD5Util.MD5(record.getUserPassword()));
        //3.响应校验
        if (userMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<User> login(UserDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "login");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        User user = userMapper.login(recordDTO.getLoginKey(), MD5Util.MD5(recordDTO.getUserPassword()));
        //3.响应校验
        if (user == null) {
            return ResponseBOUtil.fail(Errors.USER_LOGIN_ERROR);
        }
        if (user.getStatus() == 0) {
            return ResponseBOUtil.fail(Errors.SYSTEM_NO_ACCESS);
        }
        return ResponseBOUtil.success(user);
    }

    @Override
    public ResponseBO<User> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(userMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<User> userList = userMapper.selectByExample(example);
        return PageUtil.page(userList);
    }

    @Override
    public PageResponseBO selectListByPhone(String phone, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 1);
        criteria.andLike("createBy", "%" + phone + "%");
        example.orderBy("createDate").desc();

        List<User> userList = userMapper.selectByExample(example);
        return PageUtil.page(userList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        User record = new User();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(userMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(UserDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        User user = new User(recordDTO);
        if (!StringUtil.isEmpty(user.getUserPassword())) {
            user.setUserPassword(MD5Util.MD5(user.getUserPassword()));
        }
        //3.响应校验
        if (userMapper.updateByPrimaryKeySelective(user) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy) {
        int updateCount = userMapper.updateByPassword(id, MD5Util.MD5(oldPassword), MD5Util.MD5(newPassword), updateBy);
        if (updateCount == 0) {
            return ResponseBOUtil.fail(Errors.USER_OLD_PASSWORD_ERROR);
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    private Errors validRequest(UserDTO recordDTO, String type) {
        User validRequest;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case "insertSelective":
                criteria.andEqualTo("userPhone", recordDTO.getUserPhone());
                validRequest = userMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return Errors.USER_MOBILE_EXISTS;
                }
                criteria.orEqualTo("userAccount", recordDTO.getUserAccount());
                validRequest = userMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return Errors.USER_USERNAME_SAME;
                }
                break;
            case "login":
                if (StringUtil.isEmpty(recordDTO.getLoginKey()) || StringUtil.isEmpty(recordDTO.getUserPassword())) {
                    return Errors.SYSTEM_REQUEST_PARAM_ERROR;
                }
                break;
            case "updateByPrimaryKeySelective":
                if (!StringUtil.isEmpty(recordDTO.getUserPhone())) {
                    criteria.andEqualTo("userPhone", recordDTO.getUserPhone());
                    validRequest = userMapper.selectOneByExample(example);
                    if (validRequest != null && !validRequest.getUserId().equals(recordDTO.getUserId())) {
                        return Errors.USER_MOBILE_EXISTS;
                    }
                }
                break;
            default:
                return Errors.SUCCESS;
        }
        return Errors.SUCCESS;
    }
}
