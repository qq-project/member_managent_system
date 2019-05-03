package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CurrentUserInfoUtil;
import com.qiqi.member_management.management.business.biz.FileUploadBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * FileUploadController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月15号 13:19
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired
    private FileUploadBiz fileUploadBiz;
    /**
     * userAvartarUpload(用户头像上传)
     *
     * @Param 
     * @param request
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-15 13:21:07
     **/
    @RequestMapping("/userAvartarUpload")
    public ResponseDto userAvartarUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
        // 获取当前登录用户
        UserInfo userInfo = CurrentUserInfoUtil.getCurrentUser();
        MultipartFile file = multipartRequest.getFile("avartar" +userInfo.getEmail());
        ResponseDto responseDto = getResponseDto(file);
        // 处理图片 保存到本地
        if(null != responseDto){
            return responseDto;
        }
        return fileUploadBiz.userAvartarUpload(file);
    }

    /**
     * productUpload(商品图片上传)
     *
     * @Param 
     * @param request
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-19 18:58:51
     **/
    @RequestMapping("/productUpload")
    public ResponseDto productUpload(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        // 获取上传的文件
        MultipartFile file = multipartHttpServletRequest.getFile("productImg");
        ResponseDto responseDto = getResponseDto(file);
        if (null != responseDto){
            return responseDto;
        }
        return fileUploadBiz.productUpload(file);
    }

    /**
     * getResponseDto(统一处理文件上传失败响应)
     *
     * @Param 
     * @param file
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-19 19:04:53
     **/
    public ResponseDto getResponseDto(MultipartFile file){
        ResponseDto responseDto = null;
        if (null == file || file.isEmpty()){
            responseDto = new ResponseDto();
            responseDto.setResCode("100005");
            responseDto.setResMsg(MsgManagement.getMsg(100005));
            return responseDto;
        }
        return responseDto;
    }

}
