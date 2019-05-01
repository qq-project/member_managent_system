package com.qiqi.member_management.management.business.dto.request;

/**
 * UserListRequestDto类简述
 *
 * @Description 用户列表请求DTO
 * @Author shen.chu1
 * @Date 2019年05月01号 17:16
 * @Version 1.0.0
 */
public class UserListRequestDto {

    private String nickname;

    private String email;

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
}
