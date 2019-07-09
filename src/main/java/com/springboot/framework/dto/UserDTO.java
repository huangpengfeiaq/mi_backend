package com.springboot.framework.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:48
 */
@Data
public class UserDTO {
    /**
     * 用户id
     */
    private Integer userId;
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
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private Byte status;

    private String loginKey;

    //新增
    public UserDTO() {
    }


    //新增
    public UserDTO(String userIcon, String userName, Integer userSex, Date userBirthday, String userAchievement, String userAccount, String userPassword, String userPhone, String userWechatOpenid, String createBy) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userAchievement = userAchievement;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userWechatOpenid = userWechatOpenid;
        this.createBy = createBy;
    }

    //登陆
    public UserDTO(String loginKey, String userPassword) {
        this.userPassword = userPassword;
        this.loginKey = loginKey;
    }

    //更新
    public UserDTO(Integer userId, String userIcon, String userName, Integer userSex, Date userBirthday, String userAchievement, String userAccount, String userPassword, String userPhone, String userWechatOpenid, String updateBy) {
        this.userId = userId;
        this.userIcon = userIcon;
        this.userName = userName;
        this.userSex = userSex;
        this.userBirthday = userBirthday;
        this.userAchievement = userAchievement;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userWechatOpenid = userWechatOpenid;
        this.updateBy = updateBy;
    }
}
