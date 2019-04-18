package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.management.business.biz.FileUploadBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;

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
        MultipartFile file = multipartRequest.getFile("avartar");
        if (null == file || file.isEmpty()){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResCode("100005");
            responseDto.setResMsg(MsgManagement.getMsg(100005));
            return responseDto;
        }
        // 处理图片 保存到本地
        return fileUploadBiz.userAvartarUpload(file);
    }
}
