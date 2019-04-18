package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.mapper.StorageInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StorageInfoBiz类简述
 * 库存信息业务处理列
 * @Description
 * @Author qiqi
 * @Date 2019年04月17号 19:33
 * @Version 1.0.0
 */
@Service
public class StorageInfoBiz {

    @Autowired
    private StorageInfoMapper storageInfoMapper;
}
