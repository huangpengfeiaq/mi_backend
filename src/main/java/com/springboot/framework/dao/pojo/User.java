package com.springboot.framework.dao.pojo;

import com.springboot.framework.dto.UserDTO;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 13:35
 */
@Data
@Table(name = "user_master")
public class User {
    /**
     * 用户id
     */
    @Id
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
    @Transient
    private String accessToken;

    public User() {
    }

    public User(UserDTO userDTO) {
        this.userId = userDTO.getUserId();
        this.userIcon = userDTO.getUserIcon();
        this.userName = userDTO.getUserName();
        this.userSex = userDTO.getUserSex();
        this.userBirthday = userDTO.getUserBirthday();
        this.userAchievement = userDTO.getUserAchievement();
        this.userAccount = userDTO.getUserAccount();
        this.userPassword = userDTO.getUserPassword();
        this.userPhone = userDTO.getUserPhone();
        this.userWechatOpenid = userDTO.getUserWechatOpenid();
        this.createBy = userDTO.getCreateBy();
        this.createDate = userDTO.getCreateDate();
        this.updateBy = userDTO.getUpdateBy();
        this.updateDate = userDTO.getUpdateDate();
        this.status = userDTO.getStatus();
    }
}