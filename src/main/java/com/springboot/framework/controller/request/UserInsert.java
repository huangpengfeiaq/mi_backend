package com.springboot.framework.controller.request;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 14:00
 */
@Data
public class UserInsert {
    /**
     * 用户头像
     */
    private String userIcon;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户性别
     */
    private Integer userSex;
    /**
     * 用户生日
     */
    private Date userBirthday;
    /**
     * 用户成就
     */
    private String userAchievement;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 用户微信openid
     */
    private String userWechatOpenid;
}
