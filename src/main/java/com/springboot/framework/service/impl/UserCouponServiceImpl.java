package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.UserCouponMapper;
import com.springboot.framework.dao.pojo.UserCoupon;
import com.springboot.framework.dto.UserCouponDTO;
import com.springboot.framework.service.UserCouponService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:27
 */
@Service
public class UserCouponServiceImpl implements UserCouponService {
    @Resource
    private UserCouponMapper userCouponMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(UserCouponDTO recordDTO) {
        //2.创建entity
        UserCoupon record = new UserCoupon(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (userCouponMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(UserCouponDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserCoupon record = new UserCoupon(recordDTO);
        //3.响应校验
        if (userCouponMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<UserCoupon> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(userCouponMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(UserCoupon.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<UserCoupon> adminList = userCouponMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        UserCoupon record = new UserCoupon();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(userCouponMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(UserCouponDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserCoupon admin = new UserCoupon(recordDTO);
        //3.响应校验
        if (userCouponMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
