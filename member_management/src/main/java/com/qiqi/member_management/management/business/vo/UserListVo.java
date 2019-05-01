package com.qiqi.member_management.management.business.vo;

/**
 * UserListVo类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年05月01号 17:22
 * @Version 1.0.0
 */
public class UserListVo {

    private Integer id;

    private String name;

    private String tel;
    
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
