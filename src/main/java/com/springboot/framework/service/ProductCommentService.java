package com.springboot.framework.service;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.pojo.ProductComment;
import com.springboot.framework.dto.ProductCommentDTO;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/7
 */
public interface ProductCommentService {
    /**
     * 删除
     *
     * @param productCommentDTO 商品评论传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> deleteByPrimaryKey(ProductCommentDTO productCommentDTO);

    /**
     * 新增
     *
     * @param productCommentDTO 商品评论传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> insertSelective(ProductCommentDTO productCommentDTO);

    /**
     * 查询
     *
     * @param id 商品评论主键
     * @return ResponseBO<ProductComment>
     */
    ResponseBO<ProductComment> selectByPrimaryKey(Integer id);

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
     * @param productCommentDTO 商品评论传输对象
     * @return ResponseBO<Errors>
     */
    ResponseBO<Errors> updateByPrimaryKeySelective(ProductCommentDTO productCommentDTO);
}
