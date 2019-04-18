package com.qiqi.member_management.common.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * AliYunOSSFileUploadUtil类简述
 *  阿里云文件上传处理
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月15号 14:44
 * @Version 1.0.0
 */
public final class AliYunOSSFileUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(AliYunOSSFileUploadUtil.class);

    private static OSSClient ossClient;
    
    public static String fileUpload(String fileDir, MultipartFile file, String suffix){
        logger.info("------------>文件名称为:  " + fileDir + "." + suffix);

        // 创建OssClient对象
        ossClient = new OSSClient(Contant.ALIYUN_OSS_ENDPOINT, Contant.ALIYUN_OSS_ACCESS_KEY_ID, Contant.ALIYUN_OSS_ACCESS_KEY_SECRET);
        logger.info("开始创建OSSClient对象，开始上传");
        URL url = null;
        ObjectMetadata metadata = null;
        InputStream in = null;
        try{
            in = file.getInputStream();
            // 创建上传的metaData
            metadata = new ObjectMetadata();
            //设置长度
            metadata.setContentLength(in.available());
            //设置Cache-Control请求头，表示用户指定的HTTP请求/回复链的缓存行为:不经过本地缓存
            metadata.setCacheControl("no-cache");
            //设置页面不缓存
            metadata.setHeader("Pragma", "no-cache");
            // 设置文件类型
            metadata.setContentType(getcontentType(suffix));
            //设置文件描述
            metadata.setContentDisposition("inline;filename=" + fileDir + "." + suffix);
            // 上传文件
            ossClient.putObject(Contant.ALIYUN_OSS_BACKET_NAME, fileDir, in, metadata);
            // 过期时间
            Date dateExpire = null;
            String[] split = fileDir.split("/");
            // 朋友圈图片,设置URL过期时间为3个月
            if("circle".equals(split[0])){
                dateExpire = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 90);
            }else{
                // 头像,设置URL过期时间为10年
                dateExpire = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            }
            // 生成url
            url = ossClient.generatePresignedUrl(Contant.ALIYUN_OSS_BACKET_NAME, fileDir, dateExpire);
        } catch(IOException e){
            logger.error(MsgManagement.getMsg(100004));
            throw new BizException("100004");
        } finally {
            // 管理 ossClient连接
            ossClient.shutdown();
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("开始创建OSSClient对象，上传成功！");
        return url.toString();
    }

    /**
     * getcontentType(判断OSS服务文件上传时文件的contentType)
     *
     * @Param 
     * @param suffix 文件后缀
     * @return java.lang.String
     * @exception 
     * @Date  2019-04-15 14:45:25
     **/
    public static String getcontentType(String suffix) {
        System.out.println("------------>文件格式为:  " + suffix);
        if (suffix.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        } else if (suffix.equalsIgnoreCase("gif")) {
            return "image/gif";
        } else if (suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("jpg")) {
            return "image/jpeg";
        } else if (suffix.equalsIgnoreCase("png")) {
            return "image/png";
        } else if (suffix.equalsIgnoreCase("html")) {
            return "text/html";
        } else if (suffix.equalsIgnoreCase("txt")) {
            return "text/plain";
        } else if (suffix.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        } else if (suffix.equalsIgnoreCase("pptx") || suffix.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        } else if (suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("doc")) {
            return "application/msword";
        } else if (suffix.equalsIgnoreCase("xml")) {
            return "text/xml";
        } else if (suffix.equalsIgnoreCase("mp3")) {
            return "audio/mp3";
        } else if (suffix.equalsIgnoreCase("amr")) {
            return "audio/amr";
        } else {
            return "text/plain";
        }
    }

    /**
     * deletePicture(删除图片)
     *
     * @Param
     * @param key
     * @return void
     * @exception
     * @Date  2019-04-15 14:36:12
     **/
    public static void deletePicture(String key){
        ossClient = new OSSClient(Contant.ALIYUN_OSS_ENDPOINT, Contant.ALIYUN_OSS_ACCESS_KEY_ID, Contant.ALIYUN_OSS_ACCESS_KEY_SECRET);
        ossClient.deleteObject(Contant.ALIYUN_OSS_BACKET_NAME, key);
        ossClient.shutdown();
    }
}
