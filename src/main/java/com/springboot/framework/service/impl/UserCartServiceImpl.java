package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.ProductMapper;
import com.springboot.framework.dao.mapper.ProductStyleMapper;
import com.springboot.framework.dao.mapper.UserCartMapper;
import com.springboot.framework.dao.mapper.UserMapper;
import com.springboot.framework.dao.pojo.ProductStyle;
import com.springboot.framework.dao.pojo.UserCart;
import com.springboot.framework.dto.UserCartDTO;
import com.springboot.framework.service.UserCartService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.util.StringUtil;
import com.springboot.framework.vo.UserCartVO;
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
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductStyleMapper productStyleMapper;

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
    public ResponseBO<Errors> insertSelective(UserCartDTO userCartDTO) {
        //1.请求校验
        Integer styleId = userCartDTO.getStyleId();
        Integer userId = userCartDTO.getUserId();
        if (userMapper.selectByPrimaryKey(userId) == null) {
            return ResponseBOUtil.fail(Errors.USER_NOT_FIND);
        }
        if (productStyleMapper.selectByPrimaryKey(styleId) == null) {
            return ResponseBOUtil.fail(Errors.PRODUCT_STYLE_NOT_FIND);
        }
        //2.创建entity
        UserCart userCart = new UserCart();
        //分类讨论：1.存在即更新（数量加一）
        userCart.setStyleId(styleId);
        userCart.setUserId(userId);
        userCart = userCartMapper.selectOne(userCart);
        if (userCart != null) {
            if (userCartMapper.updateByCartNumber(userCart.getCartId(), userCartDTO.getCartNumber(), userCartDTO.getCreateBy()) == 0) {
                return ResponseBOUtil.fail("数量增加失败");
            }
        } else {
            //分类讨论：2.不存在即新增
            userCart = new UserCart(userCartDTO);
            if (userCartMapper.insertSelective(userCart) == 0) {
                return ResponseBOUtil.fail("添加失败");
            }
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

        List<UserCart> userCartList = userCartMapper.selectByExample(example);
        return PageUtil.page(userCartList);
    }

    @Override
    public PageResponseBO selectListByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(UserCart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<UserCart> userCartList = userCartMapper.selectByExample(example);

        List<UserCartVO> userCartVOList = Lists.newArrayList();

        for (UserCart userCart : userCartList) {
            ProductStyle productStyle = productStyleMapper.selectByPrimaryKey(userCart.getStyleId());
            UserCartVO userCartVO = new UserCartVO(userCart, productStyle, productMapper.selectByPrimaryKey(productStyle.getProductId()).getProductName());
            userCartVOList.add(userCartVO);
        }
        return PageUtil.page(userCartList, userCartVOList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        UserCart record = new UserCart();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(userCartMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(UserCartDTO userCartDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        UserCart admin = new UserCart(userCartDTO);
        //3.响应校验
        if (userCartMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> updateBySession(UserCartDTO userCartDTO) {
        if (userCartDTO.getType().equals(1)) {
            if (userCartMapper.updateBySession(userCartDTO.getCartId(), userCartDTO.getUpdateBy()) == 0) {
                return ResponseBOUtil.fail("数量减小失败");
            }
        } else {
            if (userCartMapper.updateByCartNumber(userCartDTO.getCartId(), 1, userCartDTO.getUpdateBy()) == 0) {
                return ResponseBOUtil.fail("数量增加失败");
            }
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
