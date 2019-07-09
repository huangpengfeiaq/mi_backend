package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.ProductCategoryMapper;
import com.springboot.framework.dao.pojo.ProductCategory;
import com.springboot.framework.dto.ProductCategoryDTO;
import com.springboot.framework.service.ProductCategoryService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(ProductCategoryDTO recordDTO) {
        //2.创建entity
        ProductCategory record = new ProductCategory(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (productCategoryMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(ProductCategoryDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        ProductCategory record = new ProductCategory(recordDTO);
        //3.响应校验
        if (productCategoryMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<ProductCategory> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(productCategoryMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(ProductCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<ProductCategory> adminList = productCategoryMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        ProductCategory record = new ProductCategory();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(productCategoryMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(ProductCategoryDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        ProductCategory admin = new ProductCategory(recordDTO);
        //3.响应校验
        if (productCategoryMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
