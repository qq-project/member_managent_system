package com.qiqi.member_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiqi.member_management.management.business.mapper")
public class MemberManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberManagementApplication.class, args);
    }

}
