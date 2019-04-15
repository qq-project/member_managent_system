package com.qiqi.member_management.management.business.dto.request;

import javax.validation.constraints.NotBlank;

/**
 * UserRegisterDto类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月06号 15:20
 * @Version 1.0.0
 */
public class UserRegisterRequestDto {
    private String nickname;

    @NotBlank(message = "注册邮箱不能为空")
    private String email;
    @NotBlank(message = "注册密码不能为空")
    private String password;

    private String mobile;

    private String gender;

    private String address;

    private String imgSrc;

    private String adminFlag;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getAdminFlag() {
        return adminFlag;
    }

    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag;
    }

    @Override
    public String toString() {
        return "UserRegisterRequestDto{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", adminFlag='" + adminFlag + '\'' +
                '}';
    }
}
