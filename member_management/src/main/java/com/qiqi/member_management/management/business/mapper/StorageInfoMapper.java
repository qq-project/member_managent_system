package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.StorageInfo;
import java.util.List;

public interface StorageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorageInfo record);

    StorageInfo selectByPrimaryKey(Integer id);

    List<StorageInfo> selectAll();

    int updateByPrimaryKey(StorageInfo record);

    /**
     * getLastStotageInfo(获取最新的库存信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.StorageInfo
     * @exception 
     * @Date  2019-04-22 09:24:03
     **/
    StorageInfo getLastStotageInfo();

    /**
     * saveStorageInfo(新增库存信息)
     *
     * @Param 
     * @param storageInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 10:03:54
     **/
    void saveStorageInfo(StorageInfo storageInfo);

    /**
     * updateStorageInfo(根据条件更新库存表数据)
     *
     * @Param 
     * @param storageInfo
     * @return void
     * @exception 
     * @Date  2019-04-22 11:53:42
     **/
    void updateStorageInfo(StorageInfo storageInfo);

    /**
     * queryStorageByStorageId(查询库存信息)
     *
     * @Param 
     * @param storageInfo
     * @return com.qiqi.member_management.management.business.model.StorageInfo
     * @exception 
     * @Date  2019-04-22 15:14:17
     **/
    StorageInfo queryStorageByStorageId(StorageInfo storageInfo);
}