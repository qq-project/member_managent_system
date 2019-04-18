package com.qiqi.member_management;

import com.qiqi.member_management.common.constant.Contant;

import java.util.Random;

/**
 * Test类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月18号 11:01
 * @Version 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        for(int i =0;i < 10;i++){
            System.out.println(Contant.CATEGORY_TYPES[random.nextInt(3)]);
        }
    }
}
