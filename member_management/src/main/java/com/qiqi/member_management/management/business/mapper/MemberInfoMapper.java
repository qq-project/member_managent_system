package com.qiqi.member_management.management.business.mapper;

import com.qiqi.member_management.management.business.model.MemberInfo;

import java.lang.reflect.Member;
import java.util.List;

public interface MemberInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberInfo record);

    MemberInfo selectByPrimaryKey(Integer id);

    List<MemberInfo> selectAll();

    int updateByPrimaryKey(MemberInfo record);

    /**
     * updateMemberInfo(更新会员信息)
     *
     * @Param 
     * @param memberInfo
     * @return void
     * @exception 
     * @Date  2019-04-17 08:40:44
     **/
    void updateMemberInfo(MemberInfo memberInfo);

    /**
     * queryLastMemberInfo(查询最新有效会员信息)
     *
     * @Param
     * @return com.qiqi.member_management.management.business.model.MemberInfo
     * @exception
     * @Date  2019-04-17 10:33:12
     **/
    List<MemberInfo> queryLastMemberInfo();

    /**
     * saveMemberInfo(新增会员信息)
     *
     * @Param 
     * @param memberInfo
     * @return void
     * @exception 
     * @Date  2019-04-17 11:00:06
     **/
    void saveMemberInfo(MemberInfo memberInfo);

    void deleteMemberInfo(Integer id);

    /**
     * queryMemberInfoList(根据条件查询会员列表)
     *
     * @Param 
     * @param memberInfo
     * @return java.util.List<com.qiqi.member_management.management.business.model.MemberInfo>
     * @exception 
     * @Date  2019-04-17 14:50:40
     **/
    List<MemberInfo> queryMemberInfoList(MemberInfo memberInfo);
}