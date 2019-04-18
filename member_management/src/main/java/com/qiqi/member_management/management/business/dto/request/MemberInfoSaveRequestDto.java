package com.qiqi.member_management.management.business.dto.request;

import javax.validation.constraints.NotEmpty;

/**
 * MemberInfoSaveRequestDto类简述
 * 会员信息新增DTO
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 10:21
 * @Version 1.0.0
 */
public class MemberInfoSaveRequestDto {

    @NotEmpty(message = "会员名不能为空")
    private String memberName;

    private String memberPhone;

    private String birthday;

    private String memberEmail;

    private String gender;

    private Integer integral;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "MemberInfoSaveRequestDto{" +
                "memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", birthday='" + birthday + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", gender='" + gender + '\'' +
                ", integral=" + integral +
                '}';
    }
}
