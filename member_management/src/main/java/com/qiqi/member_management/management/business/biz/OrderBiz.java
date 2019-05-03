package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.OrderCreateRequestDto;
import com.qiqi.member_management.management.business.mapper.OrderMapper;
import com.qiqi.member_management.management.business.model.*;
import com.qiqi.member_management.management.business.model.model.extend.MemberIntegralInfo;
import com.qiqi.member_management.management.business.vo.OrderDayVo;
import com.qiqi.member_management.management.business.vo.OrderInfoDaysVo;
import com.qiqi.member_management.management.business.vo.OrderListVo;
import com.qiqi.member_management.management.business.vo.ProductTotalVo;
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
        MemberIntegralInfo memberIntegralInfo = memberInfoBiz.getMemberIntegralInfo(requestDto.getMemberId());
        IntegralConfig integralConfig = memberIntegralInfo.getIntegralConfig();
        // 库存更新
        handleStorageInfo(requestDto.getProductId(), requestDto.getOrderAmount());
        // 根据 订单状态判断该订单是 10 加入购物车,20 已下单
        Order order = null;
        MemberOrder memberOrder = null;
        // 判断该会员下是否已经存在状态为 加入购物车的商品订单
        if (Contant.ORDER_UNDO.equals(requestDto.getOrderStatus())){
            // 查询未下单会员订单信息
            memberOrder = memberOrderBiz.queryUnDoneMemberOrder(requestDto.getMemberId(), requestDto.getOrderStatus());
            if(null != memberOrder){
                // 根据订单编号、商品ID查询未下单订单
                order = queryOrderByProductId(memberOrder.getOrderId(), requestDto.getProductId(),Contant.ORDER_UNDO);
            }
        }
        // 此时第一次添加 或者直接购买
        if (null == order){
            handleFirstOrderInsert(requestDto, integralConfig, memberOrder);
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
     * @param integralConfig 会员积分配置
     * @return void
     * @exception 
     * @Date  2019-04-27 09:33:28
     **/
    public void handleFirstOrderInsert(OrderCreateRequestDto requestDto, IntegralConfig integralConfig, MemberOrder memberOrderParam){
        //保存订单
        Order order = new Order();
        order.setOrderAmount(requestDto.getOrderAmount());
        order.setOrderPay(requestDto.getOrderPay());
        // 真实价格
        order.setRealPay(requestDto.getOrderPay().multiply(integralConfig.getDiscount()));
        // 设置订单状态
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
            // 更新会员积分
            MemberInfo memberInfo = memberInfoBiz.queryMemberInfoByMemberId(requestDto.getMemberId());
            memberInfo.setIntegral(memberInfo.getIntegral() + (order.getOrderPay().multiply(integralConfig.getRate())).intValue());
            memberInfoBiz.updateMemberInfo(memberInfo);
        } else if(Contant.ORDER_UNDO.equals(requestDto.getOrderStatus())){
            if (null != memberOrderParam){
                orederId = memberOrderParam.getOrderId();
            } else {
                // 只有会员订单不存在未下单时，才新增一条未下单记录
                orederId = CodeBuilderUtil.codeGenerator(memberOrderBiz.getLastOrderInfo().getId(), Contant.ORDER_ID_LENGTH, Contant.ORDER_ID_PREFIX);
                memberOrderParam = new MemberOrder();
                memberOrderParam.setOrderId(orederId);
                memberOrderParam.setMemberId(requestDto.getMemberId());
                memberOrderParam.setOrderType(Contant.ORDER_UNDO);
                memberOrderBiz.saveMemberOrder(memberOrderParam);
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
    public Order queryOrderByProductId(String orderId, String productId, String orderStatus){
        Order order = new Order();
        order.setProductId(productId);
        order.setOrderStatus(orderStatus);
        order.setOrderId(orderId);
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

    /**
     * updateOrder(更新订单)
     *
     * @Param 
     * @param order
     * @return void
     * @exception 
     * @Date  2019-05-01 10:35:50
     **/
    public void updateOrder(Order order){
        orderMapper.updateOrder(order);
    }

    /**
     * cartOrderDel(删除购物车订单)
     *
     * @Param 
     * @param order
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-05-01 11:46:50
     **/
    @Transactional
    public ResponseDto cartOrderDel(Order order) {
        order.setValid(Contant.VALID_F);
        // 作废该笔订单
        orderMapper.updateOrder(order);
        // 还原商品库存
        StorageInfo storageInfo = storageInfoBiz.queryStorageByProductId(order.getProductId());
        storageInfo.setProductAmount(storageInfo.getProductAmount() + order.getOrderAmount());
        storageInfoBiz.updateStorageInfo(storageInfo);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * saleInfoProduct(统计商品销售情况)
     *
     * @Param 
     * @return java.util.List<com.qiqi.member_management.management.business.vo.ProductTotalVo>
     * @exception 
     * @Date  2019-05-03 12:15:18
     **/
    public List<ProductTotalVo> saleInfoProduct() {
        return orderMapper.saleInfoProduct();
    }

    /**
     * orderInfoProduct(请输入描述)
     *
     * @Param 
     * @return java.util.List<com.qiqi.member_management.management.business.vo.OrderInfoDaysVo>
     * @exception 
     * @Date  2019-05-03 13:50:16
     **/
    public List<OrderInfoDaysVo> orderInfoProduct() {
        return orderMapper.orderInfoProduct();
    }

    /**
     * orderBiz(每日销量图)
     *
     * @Param 
     * @return java.util.List<com.qiqi.member_management.management.business.vo.OrderDayVo>
     * @exception 
     * @Date  2019-05-03 14:55:36
     **/
    public List<OrderDayVo> orderInfoDay() {
        return orderMapper.orderInfoDay();
    }
}
