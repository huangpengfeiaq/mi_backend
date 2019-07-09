package com.springboot.framework.controller;

import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.controller.request.UserCouponInsert;
import com.springboot.framework.controller.request.UserCouponUpdateByPrimaryKey;
import com.springboot.framework.dao.pojo.UserCoupon;
import com.springboot.framework.dto.UserCouponDTO;
import com.springboot.framework.service.UserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 19:27
 */
@Api(tags = {"用户优惠券操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/userCoupon/")
public class UserCouponController extends BaseController {
    @Resource
    private UserCouponService userCouponService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        UserCouponDTO recordDTO = new UserCouponDTO();
        recordDTO.setCouponId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userCouponService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody UserCouponInsert bean, HttpServletRequest request) {
        UserCouponDTO recordDTO = new UserCouponDTO(bean.getUserId(), bean.getCouponPrice(), bean.getCouponName(), bean.getCouponConditionPrice(), bean.getCouponPeriodOfValidity(), bean.getCouponCondition(), super.getSessionUser(request).getName());
        return userCouponService.insertSelective(recordDTO);
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<UserCoupon> selectByPrimaryKey(@RequestParam Integer id) {
        return userCouponService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userCouponService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return userCouponService.selectCount();
    }

    @ApiOperation(value = "更新", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody UserCouponUpdateByPrimaryKey bean, HttpServletRequest request) {
        UserCouponDTO recordDTO = new UserCouponDTO(bean.getCouponId(), bean.getUserId(), bean.getCouponPrice(), bean.getCouponName(), bean.getCouponConditionPrice(), bean.getCouponPeriodOfValidity(), bean.getCouponCondition(), super.getSessionUser(request).getName());
        return userCouponService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        UserCouponDTO recordDTO = new UserCouponDTO();
        recordDTO.setCouponId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userCouponService.updateByPrimaryKeySelective(recordDTO);
    }
}
