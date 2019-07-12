package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.ProductStyle;
import com.springboot.framework.dto.ProductStyleDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:16
 */
public interface ProductStyleService {
    /**
     * 删除
     *
     * @param productStyleDTO 商品款式传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(ProductStyleDTO productStyleDTO);

    /**
     * 新增
     *
     * @param productStyleDTO 商品款式传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(ProductStyleDTO productStyleDTO);

    /**
     * 查询
     *
     * @param id 商品款式主键
     * @return ResponseBO<ProductStyle>
     */
    ResponseBO<ProductStyle> selectByPrimaryKey(Integer id);

    /**
     * 列表查询
     *
     * @param pageNum  页码
     * @param pageSize 页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectList(Integer pageNum, Integer pageSize);

    /**
     * 列表查询（根据商品Id查询）
     *
     * @param productId 商品Id
     * @param pageNum   页码
     * @param pageSize  页面大小
     * @return PageResponseBO
     */
    PageResponseBO selectListByProductId(Integer productId, Integer pageNum, Integer pageSize);

    /**
     * 总数查询
     *
     * @return ResponseBO<Integer>
     */
    ResponseBO<Integer> selectCount();

    /**
     * 更新
     *
     * @param productStyleDTO 商品款式传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(ProductStyleDTO productStyleDTO);
}
