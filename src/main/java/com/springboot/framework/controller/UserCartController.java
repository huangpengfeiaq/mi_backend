package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.UserCartInsert;
import com.springboot.framework.controller.request.UserCartInsertBySession;
import com.springboot.framework.controller.request.UserCartUpdateByPrimaryKey;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.dao.pojo.UserCart;
import com.springboot.framework.dto.UserCartDTO;
import com.springboot.framework.service.UserCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:03
 */
@Api(tags = {"用户购物车操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/userCart/")
public class UserCartController extends BaseController {
    @Resource
    private UserCartService userCartService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        UserCartDTO recordDTO = new UserCartDTO();
        recordDTO.setCartId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userCartService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody UserCartInsert bean, HttpServletRequest request) {
        UserCartDTO recordDTO = new UserCartDTO(bean.getUserId(), bean.getStyleId(), bean.getCartNumber(), super.getSessionUser(request).getName());
        return userCartService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "新增（登陆用户加入购物车）", notes = "")
    @PostMapping(value = "insertSelectiveBySession")
    public ResponseBO<Errors> insertSelectiveBySession(@RequestBody UserCartInsertBySession bean, HttpServletRequest request) {
        UserCartDTO recordDTO = new UserCartDTO(super.getSessionUser(request).getId(), bean.getStyleId(), bean.getCartNumber(), super.getSessionUser(request).getName());
        return userCartService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<UserCart> selectByPrimaryKey(@RequestParam Integer id) {
        return userCartService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userCartService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看列表(根据用户id)", notes = "")
    @GetMapping(value = "selectListByUserId")
    public PageResponseBO selectListByUserId(@RequestParam Integer userId, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userCartService.selectListByUserId(userId, pageNum, pageSize);
    }

    @ApiOperation(value = "查看列表(根据登陆用户)", notes = "")
    @GetMapping(value = "selectListBySession")
    public PageResponseBO selectListBySession(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpServletRequest request) {
        return userCartService.selectListByUserId(super.getSessionUser(request).getId(), pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return userCartService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody UserCartUpdateByPrimaryKey bean, HttpServletRequest request) {
        UserCartDTO recordDTO = new UserCartDTO(bean.getCartId(), bean.getUserId(), bean.getStyleId(), bean.getCartNumber(), super.getSessionUser(request).getName());
        return userCartService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        UserCartDTO recordDTO = new UserCartDTO();
        recordDTO.setCartId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userCartService.updateByPrimaryKeySelective(recordDTO);
    }
}
