package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.management.business.mapper.StorageInfoMapper;
import com.qiqi.member_management.management.business.model.StorageInfo;
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


    /**
     * getLastStorageInfo(获取最新的库存信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.StorageInfo
     * @exception 
     * @Date  2019-04-22 09:23:18
     **/
    public StorageInfo getLastStorageInfo(){
        StorageInfo storageInfo = storageInfoMapper.getLastStotageInfo();
        if (null == storageInfo || null == storageInfo.getId()){
            storageInfo = new StorageInfo();
            storageInfo.setId(0);
        }
        return storageInfo;
    }

    public void saveStorageInfo(StorageInfo storageInfo){
        storageInfoMapper.saveStorageInfo(storageInfo);
    }

    /**
     * deleteStorageInfo(更新)
     *
     * @Param 
     * @param storageInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 11:48:57
     **/
    public void updateStorageInfo(StorageInfo storageInfo) {
        storageInfoMapper.updateStorageInfo(storageInfo);
        
    }

    /**
     * queryStorageByStorageId(根据指定条件查询商品库存信息)
     *
     * @Param 
     * @param storageInfo
     * @return com.qiqi.member_management.management.business.model.StorageInfo
     * @exception 
     * @Date  2019-04-22 15:12:27
     **/
    public StorageInfo queryStorage(StorageInfo storageInfo) {
        return storageInfoMapper.queryStorageByStorageId(storageInfo);
        
    }

    
}
