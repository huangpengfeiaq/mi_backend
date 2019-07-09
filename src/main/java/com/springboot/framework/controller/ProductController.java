package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.ProductInsert;
import com.springboot.framework.controller.request.ProductUpdateByPrimaryKey;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.dao.pojo.Product;
import com.springboot.framework.dto.ProductDTO;
import com.springboot.framework.service.ProductService;
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
@Api(tags = {"商品操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/product/")
public class ProductController extends BaseController {
    @Resource
    private ProductService productService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        ProductDTO recordDTO = new ProductDTO(id, super.getSessionUser(request).getName());
        return productService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody ProductInsert bean, HttpServletRequest request) {
        ProductDTO recordDTO = new ProductDTO(bean.getCategoryId(), bean.getProductCover(), bean.getProductName(), bean.getProductPromotion(), bean.getProductIntroduction(), bean.getProductArticleContent(), super.getSessionUser(request).getName());
        return productService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<Product> selectByPrimaryKey(@RequestParam Integer id) {
        return productService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return productService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return productService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody ProductUpdateByPrimaryKey bean, HttpServletRequest request) {
        ProductDTO recordDTO = new ProductDTO(bean.getProductId(), bean.getCategoryId(), bean.getProductCover(), bean.getProductName(), bean.getProductPromotion(), bean.getProductIntroduction(), bean.getProductArticleContent(), super.getSessionUser(request).getName());
        return productService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        ProductDTO recordDTO = new ProductDTO();
        recordDTO.setProductId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productService.updateByPrimaryKeySelective(recordDTO);
    }
}
