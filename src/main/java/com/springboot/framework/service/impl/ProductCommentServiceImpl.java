package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.dao.mapper.ProductCommentMapper;
import com.springboot.framework.dao.pojo.ProductComment;
import com.springboot.framework.dto.ProductCommentDTO;
import com.springboot.framework.service.ProductCommentService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseBOUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {
    @Resource
    private ProductCommentMapper productCommentMapper;

    @Override
    public ResponseBO<Errors> deleteByPrimaryKey(ProductCommentDTO recordDTO) {
        //2.创建entity
        ProductComment record = new ProductComment(recordDTO);
        record.setStatus((byte) -1);
        //3.响应校验
        if (productCommentMapper.updateByPrimaryKeySelective(record) == 0) {
            return ResponseBOUtil.fail("删除失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<Errors> insertSelective(ProductCommentDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "insertSelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        ProductComment record = new ProductComment(recordDTO);
        //3.响应校验
        if (productCommentMapper.insertSelective(record) == 0) {
            return ResponseBOUtil.fail("添加失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseBO<ProductComment> selectByPrimaryKey(Integer id) {
        return ResponseBOUtil.success(productCommentMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBO selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Example example = new Example(ProductComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("status", -1);
        example.orderBy("createDate").desc();

        List<ProductComment> adminList = productCommentMapper.selectByExample(example);
        return PageUtil.page(adminList);
    }

    @Override
    public ResponseBO<Integer> selectCount() {
        ProductComment record = new ProductComment();
        record.setStatus((byte) 1);
        return ResponseBOUtil.success(productCommentMapper.selectCount(record));
    }

    @Override
    public ResponseBO<Errors> updateByPrimaryKeySelective(ProductCommentDTO recordDTO) {
        //1.请求校验
//        Errors errors = validRequest(recordDTO, "updateByPrimaryKeySelective");
//        if (errors.code != 0) {
//            return ResponseBOUtil.fail(errors);
//        }
        //2.创建entity
        ProductComment admin = new ProductComment(recordDTO);
        //3.响应校验
        if (productCommentMapper.updateByPrimaryKeySelective(admin) == 0) {
            return ResponseBOUtil.fail("更新失败");
        }
        return ResponseBOUtil.success(Errors.SUCCESS);
    }
}
