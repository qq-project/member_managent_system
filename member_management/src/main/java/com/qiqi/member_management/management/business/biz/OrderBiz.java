package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.mapper.OrderMapper;
import com.qiqi.member_management.management.business.model.*;
import com.qiqi.member_management.management.business.vo.OrderListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OrderBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月25号 8:16
 * @Version 1.0.0
 */
@Service
public class OrderBiz {

    private static final Logger logger = LoggerFactory.getLogger(OrderBiz.class);
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MemberInfoBiz memberInfoBiz;
    @Autowired
    private IntegralConfigBiz integralConfigBiz;
    @Autowired
    private MemberOrderBiz memberOrderBiz;
    @Autowired
    private StorageInfoBiz storageInfoBiz;

    /**
     * createOrder(创建订单)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-25 08:43:59
     **/
    @Transactional
    public ResponseDto createOrder(OrderCreateRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 根据memberId查询会员详情
        MemberInfo memberInfo = memberInfoBiz.queryMemberInfoByMemberId(requestDto.getMemberId());
        if (null == memberInfo){
            logger.error(MsgManagement.getMsg(100010));
            throw new BizException(100010);
        }
        // 查询会员积分登记
        IntegralConfig integralConfig = integralConfigBiz.queryInfoByIntegral(memberInfo.getIntegral());
        if (null == integralConfig || null == integralConfig.getLevel()){
            logger.error(MsgManagement.getMsg(100051));
            throw new BizException(100051);
        }
        // 库存更新
        handleStorageInfo(requestDto.getProductId(), requestDto.getOrderAmount());
        // 根据 订单状态判断该订单是 10 加入购物车,20 已下单
        Order order = null;
        // 加入购物车 默认 同一会员下，同一商品的 加入购物车状态只有一个
        if (Contant.ORDER_UNDO.equals(requestDto.getOrderStatus())){
            order = queryOrderByProductId(requestDto.getProductId(),Contant.ORDER_UNDO);
        }
        // 此时第一次添加 或者直接购买
        if (null == order){
            handleFirstOrderInsert(requestDto, integralConfig.getDiscount());
            // 更新会员积分
        } else {
            // 此处表示用户添加购物车同一商品
            handleAddCart(requestDto, integralConfig.getDiscount(), order);
        }
        return responseDto;
    }

    /**
     * handleFirstOrderInsert(第一次创建订单处理)
     *
     * @Param 
     * @param requestDto
     * @param discount 会员享受折扣
     * @return void
     * @exception 
     * @Date  2019-04-27 09:33:28
     **/
    public void handleFirstOrderInsert(OrderCreateRequestDto requestDto, BigDecimal discount){
        //保存订单
        Order order = new Order();
        order.setOrderAmount(requestDto.getOrderAmount());
        order.setOrderPay(requestDto.getOrderPay());
        // 真实价格
        order.setRealPay(requestDto.getOrderPay().multiply(discount));
        // 已下单
        order.setOrderStatus(requestDto.getOrderStatus());
        order.setProductId(requestDto.getProductId());
        String orederId = "";
        // 判断会员订单是否存在未下单
        if (Contant.ORDER_DONE.equals(requestDto.getOrderStatus())){
            orederId = CodeBuilderUtil.codeGenerator(memberOrderBiz.getLastOrderInfo().getId()
                    , Contant.ORDER_ID_LENGTH, Contant.ORDER_ID_PREFIX);
            MemberOrder memberOrder = new MemberOrder();
            memberOrder.setOrderId(orederId);
            memberOrder.setMemberId(requestDto.getMemberId());
            // 设置订单编号
            memberOrder.setOrderTotalAmount(requestDto.getOrderAmount());
            memberOrder.setOrderTotalPay(requestDto.getOrderPay());
            memberOrder.setRealTotalPay(order.getRealPay());
            // 下单
            memberOrder.setOrderType(Contant.ORDER_DONE);
            memberOrderBiz.saveMemberOrder(memberOrder);
        } else if(Contant.ORDER_UNDO.equals(requestDto.getOrderStatus())){
            MemberOrder memberOrder = memberOrderBiz.queryUnDoneMemberOrder(requestDto.getMemberId(), requestDto.getOrderStatus());
            if (null != memberOrder){
                orederId = memberOrder.getOrderId();
            } else {
                // 只有会员订单不存在未下单时，才新增一条未下单记录
                orederId = CodeBuilderUtil.codeGenerator(memberOrderBiz.getLastOrderInfo().getId(), Contant.ORDER_ID_LENGTH, Contant.ORDER_ID_PREFIX);
                memberOrder = new MemberOrder();
                memberOrder.setOrderId(orederId);
                memberOrder.setMemberId(requestDto.getMemberId());
                memberOrder.setOrderType(Contant.ORDER_UNDO);
                memberOrderBiz.saveMemberOrder(memberOrder);
            }
        }
        order.setOrderId(orederId);
        saveOrder(order);
    }

    /**
     * handleAddCart(添加购物车请求)
     *
     * @Param 
     * @param requestDto
     * @param discount
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-27 09:39:27
     **/
    public void handleAddCart(OrderCreateRequestDto requestDto, BigDecimal discount, Order order){
        // 订单数量增加
        order.setOrderAmount(order.getOrderAmount() + requestDto.getOrderAmount());
        order.setOrderPay(order.getOrderPay().add(requestDto.getOrderPay()));
        // 真实价格 (原 + 新) * dis
        order.setRealPay(order.getRealPay().add(requestDto.getOrderPay()).multiply(discount));
        // 未下单
        order.setOrderStatus(Contant.ORDER_UNDO);
        // 修改原订单
        orderMapper.updateOrder(order);
    }

    /**
     * saveOrder(新增订单信息)
     *
     * @Param 
     * @param order
     * @return void
     * @exception 
     * @Date  2019-04-25 09:44:19
     **/
    public void saveOrder(Order order){
        order.setAddedByName(Contant.ADDED_NAME);
        order.setLastModifiedByName(Contant.ADDED_NAME);
        orderMapper.saveOrder(order);
    }

    /**
     * queryOrderByProductId(根据商品编号和订单状态查询订单信息)
     *
     * @Param 
     * @param productId
     * @param orderStatus
     * @return com.qiqi.member_management.management.business.model.Order
     * @exception 
     * @Date  2019-04-27 09:20:57
     **/
    public Order queryOrderByProductId(String productId, String orderStatus){
        Order order = new Order();
        order.setProductId(productId);
        order.setOrderStatus(orderStatus);
        return orderMapper.queryOrder(order);
    }

    /**
     * handleStorageInfo(处理商品库存信息)
     *
     * @Param 
     * @param productId
     * @param orderAmount
     * @return void
     * @exception 
     * @Date  2019-04-27 10:40:46
     **/
    public void handleStorageInfo(String productId, Integer orderAmount){
        // 查询商品库存信息
        StorageInfo storageInfo = storageInfoBiz.queryStorageByProductId(productId);
        if (null == storageInfo){
            logger.error(MsgManagement.getMsg(100034));
            throw new BizException(100034);
        }
        // 更新商品数量
        if (storageInfo.getProductAmount() < orderAmount){
            logger.error(MsgManagement.getMsg(100035));
            throw new BizException(100035);
        }
        storageInfo.setProductAmount(storageInfo.getProductAmount() - orderAmount);
        storageInfoBiz.updateStorageInfo(storageInfo);
    }

    /**
     * queryOrderLists(根据管理订单号和订单类型查询订单列表)
     *
     * @Param 
     * @param orderId
     * @param orderType
     * @return java.util.List<com.qiqi.member_management.management.business.model.Order>
     * @exception 
     * @Date  2019-04-27 11:30:03
     **/
    public List<OrderListVo> queryOrderLists(String orderId, String orderType) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderId", orderId);
        params.put("orderType", orderType);
        return orderMapper.queryOrderLists(params);
    }
}
