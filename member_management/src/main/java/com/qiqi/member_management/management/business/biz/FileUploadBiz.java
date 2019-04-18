package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.util.AliYunOSSFileUploadUtil;
import com.qiqi.member_management.common.util.CurrentUserInfoUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * FileUploadController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月15号 13:54
 * @Version 1.0.0
 */
@Service
public class FileUploadBiz {

    @Autowired
    private UserInfoBiz userInfoBiz;

    /**
     * userAvartarUpload(上传头像)
     *
     * @Param 
     * @param file 上传的文件
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-15 17:40:45
     **/
    public ResponseDto userAvartarUpload(MultipartFile file){
        // 阿里云文件上传
        String url = AliYunOSSFileUploadUtil.fileUpload(file.getName(), file, file.getContentType());
        UserInfo userInfo = CurrentUserInfoUtil.getCurrentUser();
        userInfo.setImgSrc(url);
        // 更新用户头像
        userInfoBiz.updateImgSrc(userInfo);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(userInfo);
        return responseDto;
    }
}
