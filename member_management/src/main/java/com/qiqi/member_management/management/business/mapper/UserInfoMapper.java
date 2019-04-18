package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.dto.request.UserModifiedRequestDto;
import com.qiqi.member_management.management.business.model.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    List<UserInfo> selectAll();

    int updateByPrimaryKey(UserInfo record);

    /**
     * userInfoMapper(根据注册邮箱查询用户登录信息)
     *
     * @Param 
     * @param email
     * @return com.qiqi.member_management.management.business.model.UserInfo
     * @exception 
     * @Date  2019-04-13 12:28:33
     **/
    UserInfo queryUserInfoByEmail(String email);

    int insertSelective(UserInfo userInfo);

    int updateimgSrc(UserInfo userInfo);

    /**
     * updateUserInfo(更新用户信息)
     *
     * @Param 
     * @param requestDto
     * @return void
     * @exception 
     * @Date  2019-04-16 11:35:40
     **/
    void updateUserInfo(UserModifiedRequestDto requestDto);
}