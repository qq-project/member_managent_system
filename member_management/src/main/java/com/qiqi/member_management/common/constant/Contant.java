package com.qiqi.member_management.common.constant;

/**
 * Contant类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月13号 12:49
 * @Version 1.0.0
 */
public class Contant {

    /**
     * 系统管理员
     **/
    public static final String ADDED_NAME = "qiqi";

    /**
     * 有效
     **/
    public static final String VALID_T = "T";

    /**
     * 有效
     **/
    public static final String VALID_F = "T";
    
    /**
     * 阿里云地域节点
     **/
    public static final String ALIYUN_OSS_ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    /**
     * 阿里云个人通行key
     **/
    public static final String ALIYUN_OSS_ACCESS_KEY_ID = "LTAILFfm6C9zjLYO";
    /**
     * 阿里云个人同行秘钥
     **/
    public static final String ALIYUN_OSS_ACCESS_KEY_SECRET = "PU6CYjD1ADMV4kdtibt13ybh21DUvy";
    /**
     * 阿里云 oss项目名
     **/
    public static final String ALIYUN_OSS_BACKET_NAME  = "qiqi-management";
    /**
     * 阿里云保存目录
     **/
    public static final String ALIYUN_OSS_FOLDER = "data/";

    /**
     * 会员编号前缀
     **/
    public static String MEMBER_ID_PREFIX = "M";
    /**
     * 会员编号总长度
     **/
    public static Integer MEMBER_ID_LENGTH = 7;

    /**
     * 类别编号前缀
     **/
    public static String CATEGORY_ID_PREFIX = "C";
    /**
     * 类别编号总长度
     **/
    public static Integer CATEGORY_ID_LENGTH = 7;
    
    public static String[] CATEGORY_TYPES = {"primary", "danger", "success"};


}
