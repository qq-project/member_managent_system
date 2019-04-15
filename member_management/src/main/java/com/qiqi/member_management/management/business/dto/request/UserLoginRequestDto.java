package com.qiqi.member_management.management.business.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * LoginRequestDto类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 14:34
 * @Version 1.0.0
 */
public class UserLoginRequestDto {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "用户密码不能为空")
    private String password;
    private boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "UserLoginRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
