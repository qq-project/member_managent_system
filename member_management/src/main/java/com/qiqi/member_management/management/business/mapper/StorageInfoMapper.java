package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.StorageInfo;
import java.util.List;

public interface StorageInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorageInfo record);

    StorageInfo selectByPrimaryKey(Integer id);

    List<StorageInfo> selectAll();

    int updateByPrimaryKey(StorageInfo record);
}