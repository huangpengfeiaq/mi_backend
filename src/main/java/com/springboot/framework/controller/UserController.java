package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.bo.PageResponseBO;
import com.springboot.framework.bo.ResponseBO;
import com.springboot.framework.bo.UserBO;
import com.springboot.framework.constant.Const;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.controller.request.UserInsert;
import com.springboot.framework.controller.request.UserLogin;
import com.springboot.framework.controller.request.UserUpdateByPrimaryKey;
import com.springboot.framework.dao.pojo.User;
import com.springboot.framework.dto.UserDTO;
import com.springboot.framework.service.UserService;
import com.springboot.framework.service.RedisService;
import com.springboot.framework.util.ResponseBOUtil;
import com.springboot.framework.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:57
 */
@Api(tags = {"用户操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @ApiOperation(value = "删除", notes = "")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseBO<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        UserDTO recordDTO = new UserDTO();
        recordDTO.setUserId(id);
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userService.deleteByPrimaryKey(recordDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "新增/注册", notes = "")
    @PostMapping(value = "insertSelective")
    public ResponseBO<Errors> insertSelective(@RequestBody UserInsert bean, HttpServletRequest request) {
        UserDTO recordDTO = new UserDTO(bean.getUserIcon(), bean.getUserName(), bean.getUserSex(), bean.getUserBirthday(), bean.getUserAchievement(), bean.getUserAccount(), bean.getUserPassword(), bean.getUserPhone(), bean.getUserWechatOpenid(), super.getSessionUser(request).getName());
        return userService.insertSelective(recordDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "登陆", notes = "")
    @PostMapping(value = "login")
    public ResponseBO<UserBO> login(@Valid @RequestBody UserLogin bean, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }
        Boolean flag = verifyCode(bean.getVerifyCode());
        if (!flag) {
            return ResponseBOUtil.fail("验证码错误");
        }
        UserDTO recordDTO = new UserDTO(bean.getLoginKey(), bean.getLoginPwd());
        ResponseBO<User> response = userService.login(recordDTO);
        if (response.isSuccess()) {
            UserBO userBO = new UserBO(response.getData());
            return accessToken(userBO, request);
        }
        return ResponseBOUtil.fail("登陆失败");
    }

    @ApiOperation(value = "退出登录", notes = "")
    @PostMapping(value = "logout")
    public ResponseBO<Void> logout(HttpServletRequest request) {
        deleteSessionUser(request);
        return ResponseBOUtil.success();
    }

    @ApiOperation(value = "查看个人信息", notes = "")
    @GetMapping(value = "selectBySession")
    public ResponseBO<UserBO> selectBySession(HttpServletRequest request) {
        UserBO user = super.getSessionUser(request);
        if (user != null) {
            return ResponseBOUtil.success(user);
        }
        return ResponseBOUtil.fail("用户未登录，无法获取当前用户信息");
    }

    @ApiOperation(value = "查看", notes = "")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseBO<User> selectByPrimaryKey(@RequestParam Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看列表", notes = "")
    @GetMapping(value = "selectList")
    public PageResponseBO selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "查看总数", notes = "")
    @GetMapping(value = "selectCount")
    public ResponseBO<Integer> selectCount() {
        return userService.selectCount();
    }

    @ApiOperation(value = "根据手机号码查看列表", notes = "")
    @GetMapping(value = "selectListByPhone")
    public PageResponseBO selectListByPhone(@RequestParam String phone, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return userService.selectListByPhone(phone, pageNum, pageSize);
    }

    @ApiOperation(value = "更新信息", notes = "")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseBO<Errors> updateByPrimaryKeySelective(@RequestBody UserUpdateByPrimaryKey bean, HttpServletRequest request) {
        UserDTO recordDTO = new UserDTO(bean.getUserId(), bean.getUserIcon(), bean.getUserName(), bean.getUserSex(), bean.getUserBirthday(), bean.getUserAchievement(), bean.getUserAccount(), bean.getUserPassword(), bean.getUserPhone(), bean.getUserWechatOpenid(), super.getSessionUser(request).getName());
        return userService.updateByPrimaryKeySelective(recordDTO);
    }

//    @ApiOperation(value = "更新个人密码", notes = "")
//    @PutMapping(value = "updateByPassword")
//    public ResponseBO<Errors> updateByPassword(@RequestBody UserUpdateByPassword bean, HttpServletRequest request) {
//        return userService.updateByPassword(super.getSessionUser(request).getId(), bean.getOldPassword(), bean.getNewPassword(), super.getSessionUser(request).getName());
//    }
//
//    @ApiOperation(value = "更新个人手机号", notes = "")
//    @PutMapping(value = "updateByPhone")
//    public ResponseBO<Errors> updateByPhone(@RequestParam String phone, HttpServletRequest request) {
//        UserDTO recordDTO = new UserDTO(super.getSessionUser(request).getId(), null, phone, null, super.getSessionUser(request).getName(), null);
//        return userService.updateByPrimaryKeySelective(recordDTO);
//    }

    @ApiOperation(value = "更新状态", notes = "")
    @PutMapping(value = "updateByStatus")
    public ResponseBO<Errors> updateByStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        UserDTO recordDTO = new UserDTO();
        recordDTO.setUserId(bean.getId());
        recordDTO.setStatus(bean.getStatus());
        recordDTO.setUpdateBy(super.getSessionUser(request).getName());
        return userService.updateByPrimaryKeySelective(recordDTO);
    }

    private Boolean verifyCode(String verifyCode) {
        if (redisService.get(Const.VERIFY_CODE) != null) {
            String randomCode = redisService.get(Const.VERIFY_CODE).toString();
            if (StringUtil.isNotBlank(randomCode) && verifyCode.toUpperCase().equals(randomCode.toUpperCase())) {
                redisService.delete(Const.VERIFY_CODE);
                return true;
            }
        }
        return false;
    }

    private ResponseBO<UserBO> accessToken(UserBO userBO, HttpServletRequest request) {
        //session.setAttribute(Const.CURRENT_USER, response.getData());
        // 创建访问token
        String accessToken = super.generateAccessToken(request);
        userBO.setAccessToken(accessToken);
        super.setAccessTokenAttribute(request, accessToken);
        super.setSessionUser(request, userBO);
        return ResponseBOUtil.success(userBO);
    }
}
