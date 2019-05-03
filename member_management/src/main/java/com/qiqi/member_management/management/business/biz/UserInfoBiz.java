package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CurrentUserInfoUtil;
import com.qiqi.member_management.common.util.MD5Util;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.UserListRequestDto;
import com.qiqi.member_management.management.business.dto.request.UserModifiedRequestDto;
import com.qiqi.member_management.management.business.dto.request.UserRegisterRequestDto;
import com.qiqi.member_management.management.business.mapper.UserInfoMapper;
import com.qiqi.member_management.management.business.model.UserInfo;
import com.qiqi.member_management.management.business.vo.UserListShowVo;
import com.qiqi.member_management.management.business.vo.UserListVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.MD5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(UserInfoBiz.class);
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

    /**
     * queryUserInfo(查询当前登录用户)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-15 15:28:58
     **/
    public ResponseDto queryUserInfo() {
        ResponseDto responseDto = new ResponseDto();
        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        if (null == subject || null == subject.getPrincipal()){
            // 表示当前用户未登录
            throw new BizException(300000);
        }
        // 获取当前用户
        UserInfo userInfo = CurrentUserInfoUtil.getCurrentUser();
        responseDto.setResult(userInfo);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }
    /**
     * updateImgSrc(更新用户头像)
     *
     * @Param 
     * @param userInfo
     * @return void
     * @exception 
     * @Date  2019-04-15 15:18:29
     **/
    public void updateImgSrc(UserInfo userInfo){
        userInfoMapper.updateimgSrc(userInfo);
    }

    /**
     * saveUserInfoRequest(更新当前用户信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-16 11:32:05
     **/
    @Transactional
    public ResponseDto saveUserInfo(UserModifiedRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        // 获取当前登录用户
        String password = requestDto.getPassword();
        /**
         * 1、当用户未进行密码修改时，直接进行新增
         * 2、用户选择密码修改 email + password 新密码
         **/
        if(StringUtils.isNotBlank(password)){
            UserInfo currentUser = CurrentUserInfoUtil.getCurrentUser();
            requestDto.setPassword(MD5Util.encrypt(currentUser.getEmail(), password));
        }
        userInfoMapper.updateUserInfo(requestDto);
        // 防止普通更新报错，待优化；
        return responseDto;
    }

    /**
     * userList(查询用户列表)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 17:18:24
     **/
    public ResponseDto userList(UserListRequestDto requestDto) {
        List<UserListVo> userInfoList = userInfoMapper.userList(requestDto);
        // 遍历列表 将当前登录用户筛选出来
        Iterator<UserListVo> iterator = userInfoList.iterator();
        List<UserListVo> userInfoDisabledList = new ArrayList<>();
        while(iterator.hasNext()){
            UserListVo userListVo = iterator.next();
            if (CurrentUserInfoUtil.getCurrentUser().getId().equals(userListVo.getId())){
                userInfoDisabledList.add(userListVo);
                // 当前用户  筛除
                iterator.remove();
            }
        }
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        UserListShowVo userListShowVo = new UserListShowVo();
        userListShowVo.setUserListVoList(userInfoList);
        userListShowVo.setUserListDisabledVoList(userInfoDisabledList);
        responseDto.setResult(userListShowVo);
        return responseDto;
    }

    /**
     * userDetail(查询用户详情)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 10:26:36
     **/
    public ResponseDto userDetail(UserInfo userInfo) {
        ResponseDto responseDto = new ResponseDto();
        // 查询数据库
        userInfo = userInfoMapper.userDetail(userInfo);
        if (null == userInfo){
            logger.error(MsgManagement.getMsg(100070));
            throw new BizException(100070);
        }
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(userInfo);
        return responseDto;
    }

    /**
     * updateUserInfo(修改用户信息)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 10:57:11
     **/
    public ResponseDto updateUserInfo(UserInfo userInfo) {
        // 根据id查询用户信息
        if (null == userInfoMapper.userDetail(userInfo)){
            logger.error(MsgManagement.getMsg(100070));
            throw new BizException(100070);
        }
        // 类型转换
        userInfoMapper.updateUserInfo(convertUserInfo(userInfo));
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * convertUserInfo(格式转换)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.request.UserModifiedRequestDto
     * @exception 
     * @Date  2019-05-02 10:59:25
     **/
    private UserModifiedRequestDto convertUserInfo(UserInfo userInfo){
        UserModifiedRequestDto requestDto = new UserModifiedRequestDto();
        requestDto.setId(userInfo.getId());
        requestDto.setNickname(userInfo.getNickname());
        requestDto.setMobile(userInfo.getMobile());
        requestDto.setAddress(userInfo.getAddress());
        requestDto.setLastModifiedByName(Contant.ADDED_NAME);
        return requestDto;
    }

    /**
     * delUserInfo(删除用户信息)
     *
     * @Param 
     * @param userInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-02 11:40:32
     **/
    public ResponseDto delUserInfo(UserInfo userInfo) {
        // 校验用户信息
        userInfo = userInfoMapper.userDetail(userInfo);
        if (null == userInfo){
            logger.error(MsgManagement.getMsg(100070));
            throw new BizException(100070);
        }
        // 校验删除用户是否是当前登录用户
        if (userInfo.getEmail().equals(CurrentUserInfoUtil.getCurrentUser().getEmail())){
            logger.error(MsgManagement.getMsg(100071));
            throw new BizException(100071);
        }
        // 删除操作
        userInfoMapper.delUserInfo(userInfo.getId());
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }
}
