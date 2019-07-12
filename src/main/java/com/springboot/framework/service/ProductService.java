package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.Product;
import com.springboot.framework.dto.ProductDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/7
 */
public interface ProductService {
    /**
     * 删除
     *
     * @param productDTO 商品传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(ProductDTO productDTO);

    /**
     * 新增
     *
     * @param productDTO 商品传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(ProductDTO productDTO);

    /**
     * 查询
     *
     * @param id 商品主键
     * @return ResponseBO<Product>
     */
    ResponseBO<Product> selectByPrimaryKey(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    /**
     * 列表查询（根据商品品类查询）
     *
     * @param categoryId  商品品类Id
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectListByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @return ResponseBO<Integer>
     */
    ResponseBO<Integer> selectCount();

    /**
     * 更新
     *
     * @param productDTO 商品传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(ProductDTO productDTO);

    /**
     * 更新（状态）
     *
     * @param productDTO 商品传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByStatus(ProductDTO productDTO);
}
