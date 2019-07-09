package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.controller.request.UserAddressInsert;
import com.springboot.framework.controller.request.UserAddressUpdateByPrimaryKey;
import com.springboot.framework.dao.pojo.UserAddress;
import com.springboot.framework.dto.UserAddressDTO;
import com.springboot.framework.service.UserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 18:52
 */
@Api(tags = {"用户收货地址操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/userAddress/")
public class UserAddressController extends BaseController {
    @Resource
    private UserAddressService userAddressService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        UserAddressDTO recordDTO = new UserAddressDTO();
        recordDTO.setAddressId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userAddressService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody UserAddressInsert bean, HttpServletRequest request) {
        UserAddressDTO recordDTO = new UserAddressDTO(bean.getUserId(), bean.getAddressName(), bean.getAddressPhone(), bean.getAddressArea(), bean.getAddressAddress(), super.getSessionUser(request).getName());
        return userAddressService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<UserAddress> selectByPrimaryKey(@RequestParam Integer id) {
        return userAddressService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userAddressService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return userAddressService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody UserAddressUpdateByPrimaryKey bean, HttpServletRequest request) {
        UserAddressDTO recordDTO = new UserAddressDTO(bean.getAddressId(), bean.getUserId(), bean.getAddressName(), bean.getAddressPhone(), bean.getAddressArea(), bean.getAddressAddress(), super.getSessionUser(request).getName());
        return userAddressService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        UserAddressDTO recordDTO = new UserAddressDTO();
        recordDTO.setAddressId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userAddressService.updateByPrimaryKeySelective(recordDTO);
    }
}
