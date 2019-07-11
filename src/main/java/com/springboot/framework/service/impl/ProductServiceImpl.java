package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.ProductCategoryMapper;
import com.springboot.framework.dao.mapper.ProductMapper;
import com.springboot.framework.dao.pojo.Product;
import com.springboot.framework.dao.pojo.ProductCategory;
import com.springboot.framework.dto.ProductDTO;
import com.springboot.framework.service.ProductService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.util.StringUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(ProductDTO recordDTO) {
        //2.创建entity
        Product record = new Product(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (productMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(ProductDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "insertSelective");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        Product record = new Product(recordDTO);
        //TODO 有待商榷代码⬇⬇⬇
        record.setCategoryName(productCategoryMapper.selectByPrimaryKey(recordDTO.getCategoryId()).getCategoryName());
        //3.响应校验
        if (productMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Product> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(productMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<Product> adminList = productMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        Product record = new Product();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(productMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(ProductDTO recordDTO) {
        //1.请求校验
        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        Product record = new Product(recordDTO);
        //TODO 有待商榷代码⬇⬇⬇
        record.setCategoryName(productCategoryMapper.selectByPrimaryKey(recordDTO.getCategoryId()).getCategoryName());
        //3.响应校验
        if (productMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> updateByStatus(ProductDTO productDTO) {
        //1.请求校验
        Errors errors = validRequest(productDTO, "updateByStatus");
        if (errors.code != 0) {
            return ResponseBOUtil.fail(errors);
        }
        //2.创建entity
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setStatus(productDTO.getStatus());
        product.setUpdateBy(productDTO.getUpdateBy());
        //3.响应校验
        if (productMapper.updateByPrimaryKeySelective(product) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    private Errors validRequest(ProductDTO recordDTO, String type) {
        Product validRequest;
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        switch (type) {
            case "insertSelective":
                criteria.andEqualTo("productName", recordDTO.getProductName());
                validRequest = productMapper.selectOneByExample(example);
                if (validRequest != null) {
                    return Errors.PRODUCT_NAME_SAME;
                }
                if (productCategoryMapper.selectByPrimaryKey(recordDTO.getCategoryId()) == null) {
                    return Errors.PRODUCT_CATEGORY_NOT_FIND;
                }
                break;
            case "updateByPrimaryKeySelective":
                Integer productId = recordDTO.getProductId();
                if (productMapper.selectByPrimaryKey(productId) == null) {
                    return Errors.PRODUCT_NOT_FIND;
                }
                if (productCategoryMapper.selectByPrimaryKey(recordDTO.getCategoryId()) == null) {
                    return Errors.PRODUCT_CATEGORY_NOT_FIND;
                }
                criteria.andEqualTo("productName", recordDTO.getProductName());
                validRequest = productMapper.selectOneByExample(example);
                if (validRequest != null && !validRequest.getProductId().equals(productId)) {
                    return Errors.PRODUCT_NAME_SAME;
                }
                break;
            case "updateByStatus":
                if (productMapper.selectByPrimaryKey(recordDTO.getProductId()) == null) {
                    return Errors.PRODUCT_NOT_FIND;
                }
                break;
            default:
                return Errors.SUCCESS;
        }
        return Errors.SUCCESS;
    }
}
