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
     * 无效
     **/
    public static final String VALID_F = "F";

    /**
     * 订单状态：已下单
     **/
    public static final String ORDER_DONE = "20";

    /**
     * 订单状态：加入购物车
     **/
    public static final String ORDER_UNDO = "10";
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

    /**
     * 随机类别样式
     **/
    public static String[] CATEGORY_TYPES = {"primary", "danger", "success"};

    /**
     * 商品编号前缀
     **/

    public static String PRODUCT_ID_PREFIX = "P";
    
    /**
     * 商品编号总长度
     **/
    public static Integer PRODUCT_ID_LENGTH = 7;

    /**
     * 库存编号前缀
     **/

    public static String STORAGE_ID_PREFIX = "S";

    /**
     * 库存编号总长度
     **/
    public static Integer STORAGE_ID_LENGTH = 7;

    /**
     * 库存容量大小默认为初次商品数量的10倍
     **/
    public static Integer STORAGE_SIZE = 10;


    /**
     * 订单编号前缀
     **/

    public static String ORDER_ID_PREFIX = "O";

    /**
     * 订单编号总长度
     **/
    public static Integer ORDER_ID_LENGTH = 7;

    /**
     * 订单关联编号前缀
     **/

    public static String ORDER_REL_ID_PREFIX = "OR";

    /**
     * 订单关联总长度
     **/
    public static Integer ORDER_REL_ID_LENGTH = 8;

    /**
     * 订单关联编号前缀
     **/

    public static String INTEGRAL_ID_PREFIX = "IT";

    /**
     * 订单关联总长度
     **/
    public static Integer INTEGRAL_ID_LENGTH = 8;
}
