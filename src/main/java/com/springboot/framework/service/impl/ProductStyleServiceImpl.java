package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.ProductMapper;
import com.springboot.framework.dao.mapper.ProductStyleMapper;
import com.springboot.framework.dao.pojo.ProductStyle;
import com.springboot.framework.dto.ProductStyleDTO;
import com.springboot.framework.service.ProductStyleService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.vo.ProductStyleVO;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:17
 */
@Service
public class ProductStyleServiceImpl implements ProductStyleService {
    @Resource
    private ProductStyleMapper productStyleMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(ProductStyleDTO recordDTO) {
        //2.创建entity
        ProductStyle record = new ProductStyle(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (productStyleMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(ProductStyleDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "insertSelective");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        ProductStyle record = new ProductStyle(recordDTO);
        //3.响应校验
        if (productStyleMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<ProductStyle> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(productStyleMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(ProductStyle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<ProductStyle> productStyleList = productStyleMapper.selectByExample(example);
        List<ProductStyleVO> productStyleVOList = Lists.newArrayList();
        for (ProductStyle productStyle : productStyleList) {
            ProductStyleVO productStyleVO = new ProductStyleVO(productStyle, productMapper.selectByPrimaryKey(productStyle.getProductId()).getProductName());
            productStyleVOList.add(productStyleVO);
        }

        return PageUtil.page(productStyleList, productStyleVOList);
    }

    @Override
    public PageResponseBO selectListByProductId(Integer productId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(ProductStyle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        criteria.andEqualTo("productId", productId);
        example.orderBy("createDate").desc();

        List<ProductStyle> productStyleList = productStyleMapper.selectByExample(example);
        List<ProductStyleVO> productStyleVOList = Lists.newArrayList();
        for (ProductStyle productStyle : productStyleList) {
            ProductStyleVO productStyleVO = new ProductStyleVO(productStyle, productMapper.selectByPrimaryKey(productStyle.getProductId()).getProductName());
            productStyleVOList.add(productStyleVO);
        }

        return PageUtil.page(productStyleList, productStyleVOList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        ProductStyle record = new ProductStyle();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(productStyleMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(ProductStyleDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        ProductStyle admin = new ProductStyle(recordDTO);
        //3.响应校验
        if (productStyleMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    private Errors validRequest(ProductStyleDTO recordDTO, String type) {
        ProductStyle validRequest;
        Example example = new Example(ProductStyle.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case "insertSelective":
                if (productMapper.selectByPrimaryKey(recordDTO.getProductId()) == null) {
                    return Errors.PRODUCT_CATEGORY_NOT_FIND;
                }
                break;
//            case "updateByPrimaryKeySelective":
//                Integer productId = recordDTO.getProductId();
//                if (productMapper.selectByPrimaryKey(productId) == null) {
//                    return Errors.PRODUCT_NOT_FIND;
//                }
//                if (productCategoryMapper.selectByPrimaryKey(recordDTO.getCategoryId()) == null) {
//                    return Errors.PRODUCT_CATEGORY_NOT_FIND;
//                }
//                criteria.andEqualTo("productName", recordDTO.getProductName());
//                validRequest = productMapper.selectOneByExample(example);
//                if (validRequest != null && !validRequest.getProductId().equals(productId)) {
//                    return Errors.PRODUCT_NAME_SAME;
//                }
//                break;
            default:
                return Errors.SUCCESS;
        }
        return Errors.SUCCESS;
    }
}
