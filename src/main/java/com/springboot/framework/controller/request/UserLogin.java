package com.springboot.framework.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/8 14:05
 */
@Data
public class UserLogin {
    @ApiModelProperty(value = "用户名或手机号，必填", required = true)
    private String loginKey;
    @Length(min = 3, max = 16, message = "请输入3-16位密码")
    @ApiModelProperty(value = "密码，必填", required = true)
    private String loginPwd;
    @ApiModelProperty(value = "验证码，必填", required = true)
    private String verifyCode;
}
