package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.UserCartMapper;
import com.springboot.framework.dao.pojo.UserCart;
import com.springboot.framework.dto.UserCartDTO;
import com.springboot.framework.service.UserCartService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:02
 */
@Service
public class UserCartServiceImpl implements UserCartService {
    @Resource
    private UserCartMapper userCartMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(UserCartDTO recordDTO) {
        //2.创建entity
        UserCart record = new UserCart(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (userCartMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(UserCartDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserCart record = new UserCart(recordDTO);
        //3.响应校验
        if (userCartMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<UserCart> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(userCartMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(UserCart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<UserCart> adminList = userCartMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        UserCart record = new UserCart();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(userCartMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(UserCartDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserCart admin = new UserCart(recordDTO);
        //3.响应校验
        if (userCartMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
