package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.MemberOrderListRequestDto;
import com.qiqi.member_management.management.business.mapper.MemberOrderMapper;
import com.qiqi.member_management.management.business.model.IntegralConfig;
import com.qiqi.member_management.management.business.model.MemberInfo;
import com.qiqi.member_management.management.business.model.MemberOrder;
import com.qiqi.member_management.management.business.vo.MemberOrderListVo;
import com.qiqi.member_management.management.business.vo.OrderListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * MemberOrderBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:17
 * @Version 1.0.0
 */
@Service
public class MemberOrderBiz {

    @Autowired
    private MemberOrderMapper memberOrderMapper;
    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private MemberInfoBiz memberInfoBiz;
    @Autowired
    private IntegralConfigBiz integralConfigBiz;

    private static final Logger logger = LoggerFactory.getLogger(MemberOrderBiz.class);

    /**
     * queryMemberOrderList(查询会员订单列表)
     *
     * @Param
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception
     * @Date  2019-04-27 10:54:14
     **/
    public ResponseDto queryMemberOrderList(MemberOrderListRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 实际返回的vo
        List<MemberOrderListVo> memberOrderListVoList = handleResultVo(requestDto);
        if (null == memberOrderListVoList || memberOrderListVoList.size() == 0){
            logger.error(MsgManagement.getMsg(100061));
            throw new BizException(100061);
        }
        responseDto.setResult(memberOrderListVoList);
        return responseDto;
    }

    /**
     * saveMemberOrder(保存会员订单信息)
     *
     * @Param
     * @param memberOrder
     * @return void
     * @exception
     * @Date  2019-04-25 10:16:09
     **/
    public void saveMemberOrder(MemberOrder memberOrder){
        memberOrder.setAddedByName(Contant.ADDED_NAME);
        memberOrder.setLastModifiedByName(Contant.ADDED_NAME);
        memberOrderMapper.saveMemberOrder(memberOrder);
    }


    /**
     * handleResultVo(处理返回的会员订单列表)
     *
     * @Param
     * @param requestDto
     * @return java.util.List<com.qiqi.member_management.management.business.vo.MemberOrderListVo>
     * @exception
     * @Date  2019-04-27 11:48:05
     **/
    private List<MemberOrderListVo> handleResultVo(MemberOrderListRequestDto requestDto) {
        // 查询所有的会员订单信息
        List<MemberOrder> memberOrderList = memberOrderMapper.queryMemberOrderList(requestDto);
        // 实际返回的vo
        List<MemberOrderListVo> memberOrderListVoList = new ArrayList<>();
        if (null != memberOrderList && memberOrderList.size() > 0) {
            for (MemberOrder memberOrder : memberOrderList) {
                // 根据会员编号，查询实际order
                List<OrderListVo> orderList = orderBiz.queryOrderLists(memberOrder.getOrderId(), requestDto.getOrderType());
                if (null != orderList && orderList.size() > 0) {
                    // 根据memberId 查询会员信息
                    MemberInfo memberInfo = memberInfoBiz.queryMemberInfoByMemberId(memberOrder.getMemberId());
                    if (null == memberInfo) {
                        logger.error(MsgManagement.getMsg(100010));
                        throw new BizException(100010);
                    }
                    // 查询会员积分登记
                    IntegralConfig integralConfig = integralConfigBiz.queryInfoByIntegral(memberInfo.getIntegral());
                    if (null == integralConfig || null == integralConfig.getLevel()) {
                        logger.error(MsgManagement.getMsg(100051));
                        throw new BizException(100051);
                    }
                    MemberOrderListVo memberOrderListVo = new MemberOrderListVo();
                    memberOrderListVo.setId(memberInfo.getId());
                    memberOrderListVo.setMemberName(memberInfo.getMemberName());
                    memberOrderListVo.setMemberId(memberInfo.getMemberId());
                    memberOrderListVo.setDiscount(integralConfig.getDiscount());
                    memberOrderListVo.setOrderCart(orderList);
                    memberOrderListVoList.add(memberOrderListVo);
                }
            }
        }
        return memberOrderListVoList;
    }

    /**
     * queryUnDoneMemberOrder(查询会员订单)
     *
     * @Param 
     * @param memberId
     * @param orderStatus
     * @return com.qiqi.member_management.management.business.model.MemberOrder
     * @exception 
     * @Date  2019-04-27 12:38:03
     **/
    public MemberOrder queryUnDoneMemberOrder(String memberId, String orderStatus) {
        MemberOrder memberOrder = new MemberOrder();
        memberOrder.setMemberId(memberId);
        memberOrder.setOrderType(orderStatus);
        return queryMemberOrder(memberOrder);
    }

    /**
     * queryMemberOrder(查询会员信息)
     *
     * @Param 
     * @param memberOrder
     * @return com.qiqi.member_management.management.business.model.MemberOrder
     * @exception 
     * @Date  2019-04-27 12:39:29
     **/
    public MemberOrder queryMemberOrder(MemberOrder memberOrder){
        return memberOrderMapper.queryMemberOrder(memberOrder);
    }

    /**
     * getLastOrderInfo(获取最新会员订单信息)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto
     * @exception 
     * @Date  2019-04-27 12:52:32
     **/
    public MemberOrder getLastOrderInfo() {
        return memberOrderMapper.getLastOrderInfo();
    }
}
