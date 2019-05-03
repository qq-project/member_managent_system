package com.qiqi.member_management.management.business.vo;

import java.util.List;

/**
 * UserListShowVo类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月02号 11:53
 * @Version 1.0.0
 */
public class UserListShowVo {

    private List<UserListVo> userListVoList;

    private List<UserListVo> userListDisabledVoList;

    public List<UserListVo> getUserListVoList() {
        return userListVoList;
    }

    public void setUserListVoList(List<UserListVo> userListVoList) {
        this.userListVoList = userListVoList;
    }

    public List<UserListVo> getUserListDisabledVoList() {
        return userListDisabledVoList;
    }

    public void setUserListDisabledVoList(List<UserListVo> userListDisabledVoList) {
        this.userListDisabledVoList = userListDisabledVoList;
    }
}
