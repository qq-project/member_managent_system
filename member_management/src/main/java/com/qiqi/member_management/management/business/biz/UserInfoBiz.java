package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.MD5Util;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserRegisterRequestDto;
import com.qiqi.member_management.management.business.mapper.UserInfoMapper;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserInfoBiz类简述
 *
 * @Description
 * @Author qiqi
 * @Date 2019年04月13号 12:25
 * @Version 1.0.0
 */
@Service
public class UserInfoBiz {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * queryUserByEmail(根据邮箱查询用户信息)
     *
     * @Param 
     * @param email
     * @return com.qiqi.member_management.management.business.model.UserInfo
     * @exception 
     * @Date  2019-04-13 12:27:26
     **/
    public UserInfo queryUserInfoByEmail(String email){
        return userInfoMapper.queryUserInfoByEmail(email);
    }

    /**
     * register(用户注册)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-13 12:35:15
     **/
    @Transactional
    public ResponseDto register(UserRegisterRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 判断数据库中是否存在该注册用户
        UserInfo userInfo = queryUserInfoByEmail(requestDto.getEmail());
        if (null != userInfo){
            // 表示已经存在该注册用户
            responseDto.setResCode("100003");
            responseDto.setResMsg(MsgManagement.getMsg(100003, requestDto.getEmail()));
            return responseDto;
        }
        // 密码加密
        String password = MD5Util.encrypt(requestDto.getEmail(), requestDto.getPassword());
        // 插入对象转换
        UserInfo userInfoNew = userRegisterRequestDtoConvertToUserInfo(requestDto, password);
        // 插入操作
        userInfoMapper.insertSelective(userInfoNew);
        return responseDto;
    }

    /**
     * userRegisterRequestDtoConvertToUserInfo(处理值转换)
     *
     * @Param 
     * @param requestDto
     * @param password
     * @return com.qiqi.member_management.management.business.model.UserInfo
     * @exception 
     * @Date  2019-04-13 12:47:48
     **/
    private UserInfo  userRegisterRequestDtoConvertToUserInfo(UserRegisterRequestDto requestDto, String password){
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(requestDto.getEmail());
        userInfo.setNickname(requestDto.getNickname());
        userInfo.setPassword(password);
        userInfo.setMobile(requestDto.getMobile());
        userInfo.setAddress(requestDto.getAddress());
        userInfo.setGender(requestDto.getGender());
        userInfo.setAddedByName(Contant.ADDED_NAME);
        userInfo.setLastModifiedByName(Contant.ADDED_NAME);
        return userInfo;
    }

}
