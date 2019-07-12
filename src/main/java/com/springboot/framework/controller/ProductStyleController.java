package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.ProductStyleInsert;
import com.springboot.framework.controller.request.ProductStyleUpdateByPrimaryKey;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.dao.pojo.ProductStyle;
import com.springboot.framework.dto.ProductStyleDTO;
import com.springboot.framework.service.ProductStyleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:19
 */
@Api(tags = {"商品款式操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/productStyle/")
public class ProductStyleController extends BaseController {
    @Resource
    private ProductStyleService productStyleService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        ProductStyleDTO recordDTO = new ProductStyleDTO();
        recordDTO.setStyleId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productStyleService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody ProductStyleInsert bean, HttpServletRequest request) {
        ProductStyleDTO recordDTO = new ProductStyleDTO(bean.getProductId(), bean.getStyleCover(), bean.getStylePromotionalPrice(), bean.getStylePrice(), bean.getStyleVersion(), bean.getStyleColor(), super.getSessionUser(request).getName());
        return productStyleService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<ProductStyle> selectByPrimaryKey(@RequestParam Integer id) {
        return productStyleService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return productStyleService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看列表（根据商品Id查询）", notes = "")
    @GetMapping(value = "selectListByProductId")
    public PageResponseBO selectListByProductId(@RequestParam Integer productId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return productStyleService.selectListByProductId(productId,pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return productStyleService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody ProductStyleUpdateByPrimaryKey bean, HttpServletRequest request) {
        ProductStyleDTO recordDTO = new ProductStyleDTO(bean.getStyleId(), bean.getProductId(), bean.getStyleCover(), bean.getStylePromotionalPrice(), bean.getStylePrice(), bean.getStyleVersion(), bean.getStyleColor(), super.getSessionUser(request).getName());
        return productStyleService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        ProductStyleDTO recordDTO = new ProductStyleDTO();
        recordDTO.setStyleId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return productStyleService.updateByPrimaryKeySelective(recordDTO);
    }
}