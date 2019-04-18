package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.common.util.DateConvertUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.MemberInfoSaveRequestDto;
import com.qiqi.member_management.management.business.mapper.MemberInfoMapper;
import com.qiqi.member_management.management.business.model.MemberInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * MemberInfoBiz类简述
 * 会员信息业务类
 * @Description
 * @Author qiqi
 * @Date 2019年04月16号 20:57
 * @Version 1.0.0
 */
@Service
public class MemberInfoBiz {
    @Autowired
    private MemberInfoMapper memberInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(MemberInfoBiz.class);
    /**
     * memberInfoList(查询会员列表)
     *
     * @Param
     * @param memberInfo
     * @return java.util.List<com.qiqi.member_management.management.business.model.MemberInfo>
     * @exception 
     * @Date  2019-04-16 20:58:47
     **/
    public List<MemberInfo> memberInfoList(MemberInfo memberInfo){
        return memberInfoMapper.queryMemberInfoList(memberInfo);
    }

    /**
     * updateMemberInfo(更新会员信息)
     *
     * @Param 
     * @param memberInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 08:39:45
     **/
    @Transactional
    public ResponseDto updateMemberInfo(MemberInfo memberInfo) {
        ResponseDto responseDto = new ResponseDto();
        memberInfoMapper.updateMemberInfo(memberInfo);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * saveMemberInfo(保存会员信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 10:23:24
     **/
    @Transactional
    public ResponseDto saveMemberInfo(MemberInfoSaveRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        // 查询当前最新ID
        MemberInfo memberInfo = convertMemberInfoSaveRequestDtoToMember(requestDto);
        memberInfo.setMemberId(CodeBuilderUtil.codeGenerator(queryLastMemberInfo().getId(), Contant.MEMBER_ID_LENGTH, Contant.MEMBER_ID_PREFIX));
        // 新增会员信息
        memberInfoMapper.saveMemberInfo(memberInfo);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * queryLastMemberInfo(查询当前最新一条数据)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.MemberInfo
     * @exception 
     * @Date  2019-04-17 10:40:01
     **/
    public MemberInfo queryLastMemberInfo(){
        List<MemberInfo> memberInfos = memberInfoMapper.queryLastMemberInfo();
        // 查询数据为空
        if (null == memberInfos && memberInfos.size() == 0) {
            logger.error(MsgManagement.getMsg(100010));
            throw new BizException(100010);
        }
        return memberInfos.get(0);
    }


    /**
     * convertMemberInfoSaveRequestDtoToMember(对象转换)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.model.MemberInfo
     * @exception 
     * @Date  2019-04-17 10:41:53
     **/
    private MemberInfo convertMemberInfoSaveRequestDtoToMember(MemberInfoSaveRequestDto requestDto) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberName(requestDto.getMemberName());
        memberInfo.setMemberPhone(requestDto.getMemberPhone());
        memberInfo.setGender(requestDto.getGender());
        memberInfo.setIntegral(requestDto.getIntegral());
        memberInfo.setMemberEmail(requestDto.getMemberEmail());
        // 日期转换
        memberInfo.setBirthday(DateConvertUtil.convertStringToDate(requestDto.getBirthday()));
        memberInfo.setAddedByName(Contant.ADDED_NAME);
        memberInfo.setLastModifiedByName(Contant.ADDED_NAME);
        return memberInfo;
    }

    /**
     * deleteMemberInfo(根据ID作废会员信息)
     *
     * @Param 
     * @param id
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 13:31:43
     **/
    @Transactional
    public ResponseDto deleteMemberInfo(Integer id) {
        ResponseDto responseDto = new ResponseDto();
        memberInfoMapper.deleteMemberInfo(id);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }
}
