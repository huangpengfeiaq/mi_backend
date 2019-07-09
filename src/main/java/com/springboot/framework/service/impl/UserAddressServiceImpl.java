package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.UserAddressMapper;
import com.springboot.framework.dao.pojo.UserAddress;
import com.springboot.framework.dto.UserAddressDTO;
import com.springboot.framework.service.UserAddressService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:51
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(UserAddressDTO recordDTO) {
        //2.创建entity
        UserAddress record = new UserAddress(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (userAddressMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(UserAddressDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserAddress record = new UserAddress(recordDTO);
        //3.响应校验
        if (userAddressMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<UserAddress> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(userAddressMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<UserAddress> adminList = userAddressMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        UserAddress record = new UserAddress();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(userAddressMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(UserAddressDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserAddress admin = new UserAddress(recordDTO);
        //3.响应校验
        if (userAddressMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
