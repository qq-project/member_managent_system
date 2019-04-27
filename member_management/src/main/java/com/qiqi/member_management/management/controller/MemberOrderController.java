package com.qiqi.member_management.management.controller;

import com.qiqi.member_management.management.business.biz.MemberOrderBiz;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.MemberOrderListRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemberOrderController类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月27号 10:51
 * @Version 1.0.0
 */
@RequestMapping("/memberOrder")
@RestController
public class MemberOrderController {

    @Autowired
    private MemberOrderBiz memberOrderBiz;

    /**
     * queryMemberOrderList(查询会员订单列表)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-27 10:53:14
     **/
    @RequestMapping("/queryMemberOrderList")
    public ResponseDto queryMemberOrderList(@RequestBody MemberOrderListRequestDto requestDto){
        return memberOrderBiz.queryMemberOrderList(requestDto);
    }
}
