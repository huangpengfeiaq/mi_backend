package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.ProductCommentInsert;
import com.springboot.framework.controller.request.ProductCommentUpdateByPrimaryKey;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.dao.pojo.ProductComment;
import com.springboot.framework.dto.ProductCommentDTO;
import com.springboot.framework.service.ProductCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:29
 */
@Api(tags = {"商品评论操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/productComment/")
public class ProductCommentController extends BaseController {
    @Resource
    private ProductCommentService productCommentService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        ProductCommentDTO recordDTO = new ProductCommentDTO();
        recordDTO.setCommentId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productCommentService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody ProductCommentInsert bean, HttpServletRequest request) {
        ProductCommentDTO recordDTO = new ProductCommentDTO(bean.getProductId(), bean.getUserId(), bean.getCommentStar(), bean.getCommentLike(), bean.getCommentContent(), bean.getCommentPicture(), bean.getCommentReply(), super.getSessionUser(request).getName());
        return productCommentService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<ProductComment> selectByPrimaryKey(@RequestParam Integer id) {
        return productCommentService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return productCommentService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return productCommentService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody ProductCommentUpdateByPrimaryKey bean, HttpServletRequest request) {
        ProductCommentDTO recordDTO = new ProductCommentDTO(bean.getCommentId(), bean.getProductId(), bean.getUserId(), bean.getCommentStar(), bean.getCommentLike(), bean.getCommentContent(), bean.getCommentPicture(), bean.getCommentReply(), super.getSessionUser(request).getName());
        return productCommentService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        ProductCommentDTO recordDTO = new ProductCommentDTO();
        recordDTO.setCommentId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productCommentService.updateByPrimaryKeySelective(recordDTO);
    }
}
