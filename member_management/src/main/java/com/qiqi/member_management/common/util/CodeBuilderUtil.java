package com.qiqi.member_management.common.util;

/**
 * CodeBuilderUtil类简述
 * 编号统一生成器
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月17号 10:25
 * @Version 1.0.0
 */
public class CodeBuilderUtil {

    /**
     * 补零
     **/
    private static final String SEPERATOR = "0";

    private static final Integer NEXT_ONE = 1;
    /**
     * codeGenerator(编号生成器)
     *
     * @Param 
     * @param id 主键ID
     * @param maxSize 规定最大长度
     * @param prefix 前缀
     * @return java.lang.String
     * @exception 
     * @Date  2019-04-17 10:28:06
     **/
    public static String codeGenerator(Integer id, Integer maxSize, String prefix){
         if(null == id){
             return null;
         }
         // 编号下一位
         id = id + NEXT_ONE;
         int length = maxSize - prefix.length() - id.toString().length();
         StringBuilder sb = new StringBuilder();
         sb.append(prefix);
         int start = 0;
         for (int i = 0; i < length; i++){
             sb.append(SEPERATOR);
         }
         return sb.append(id.toString()).toString();
    }
}
