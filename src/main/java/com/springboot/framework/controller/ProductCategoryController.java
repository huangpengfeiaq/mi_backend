package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.ProductCategoryInsert;
import com.springboot.framework.controller.request.ProductCategoryUpdateByPrimaryKey;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.dao.pojo.ProductCategory;
import com.springboot.framework.dto.ProductCategoryDTO;
import com.springboot.framework.service.ProductCategoryService;
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
@Api(tags = {"商品品类操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/productCategory/")
public class ProductCategoryController extends BaseController {
    @Resource
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setCategoryId(id);
        productCategoryDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productCategoryService.deleteByPrimaryKey(productCategoryDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody ProductCategoryInsert bean, HttpServletRequest request) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setCategoryName(bean.getCategoryName());
        productCategoryDTO.setCategorySort(bean.getCategorySort());
        productCategoryDTO.setCategoryCover(bean.getCategoryCover());
        productCategoryDTO.setCreateBy(super.getSessionUser(request).getName());
        return productCategoryService.insertSelective(productCategoryDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<ProductCategory> selectByPrimaryKey(@RequestParam Integer id) {
        return productCategoryService.selectByPrimaryKey(id);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return productCategoryService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return productCategoryService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody ProductCategoryUpdateByPrimaryKey bean, HttpServletRequest request) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setCategoryId(bean.getCategoryId());
        productCategoryDTO.setCategoryName(bean.getCategoryName());
        productCategoryDTO.setCategorySort(bean.getCategorySort());
        productCategoryDTO.setCategoryCover(bean.getCategoryCover());
        productCategoryDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productCategoryService.updateByPrimaryKeySelective(productCategoryDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setCategoryId(bean.getId());
        productCategoryDTO.setStatus(bean.getStatus());
        productCategoryDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productCategoryService.updateByPrimaryKeySelective(productCategoryDTO);
    }
}
