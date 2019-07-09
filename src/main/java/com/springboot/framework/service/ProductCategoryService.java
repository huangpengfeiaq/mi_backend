package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.ProductCategory;
import com.springboot.framework.dto.ProductCategoryDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/7
 */
public interface ProductCategoryService {
    /**
     * 删除
     *
     * @param productCategoryDTO 商品品类传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(ProductCategoryDTO productCategoryDTO);

    /**
     * 新增
     *
     * @param productCategoryDTO 商品品类传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(ProductCategoryDTO productCategoryDTO);

    /**
     * 查询
     *
     * @param id 商品品类主键
     * @return ResponseBO<ProductCategory>
     */
    ResponseBO<ProductCategory> selectByPrimaryKey(Integer id);

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
     * @param productCategoryDTO 商品品类传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(ProductCategoryDTO productCategoryDTO);
}
