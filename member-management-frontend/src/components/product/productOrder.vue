<!--商品详情页，下单页-->
<template>
    <div class="detail-sku">
        <van-sku
            v-model="sku_show"
            :sku="productInfoSku"
            :goods="productInfo"
            :custom-stepper-config="customStepperConfig"
            :disable-stepper-input="disableStepperInput"
            reset-stepper-on-hide
        >
            <template slot="sku-header-price">
               <div class="van-sku__goods-price">
                    <span class="van-sku__price-symbol">￥</span>
                    <span class="van-sku__price-num">{{ this.productInfo.price}}.00</span> 
               </div>
            </template>
            <template slot="sku-group">
                <div>
                </div>
            </template>
            <!--商品数量选择-->
            <template slot="sku-group">
            </template>
            <!--商品留言区-->
            <template slot="sku-messages">
                <br/><br/>
                <div class="van-cell-group van-hairline--top-bottom van-sku-messages">
                    <div class="van-cell van-cell--required van-field">
                        <div class="van-cell__title van-field__label">
                            <span>会员编号</span>
                        </div>
                        <div class="van-cell__value">
                            <div class="van-field__body">
                                <van-field  maxlength="200" placeholder="请选择会员编号" class="van-field__control" v-model="memberId" readonly @click="chosenMemberId()"/>
                            </div>
                        </div>
                    </div>
                </div>
            </template>
            <!--商品操作按钮区-->
            <template slot="sku-actions">
                <div class="van-sku-actions" v-if="isCartShow">
                    <van-button bottom-action @click="productAddCart()">加入购物车</van-button>
                </div>   
                 <div class="van-sku-actions" v-if="!isCartShow">
                     <br/><br/>
                        <van-submit-bar :price="orderPay" button-text="提交订单" @submit="productBuy()">
                            <br/><br/>
                            <div class="van-cell-group van-hairline--top-bottom van-sku-messages" slot="top">
                                <div class="van-cell van-cell--required van-field">
                                    <div class="van-cell__title van-field__label">
                                        <span>会员编号</span>
                                    </div>
                                    <div class="van-cell__value">
                                        <div class="van-field__body">
                                            <van-field  maxlength="200" placeholder="请选择会员编号" class="van-field__control" v-model="memberId" readonly @click="chosenMemberId()"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </van-submit-bar>
                </div>
            </template>
        </van-sku>
        <!--选择会员编号-->
         <van-dialog
            v-model="chosenMemberIdShow"
            show-cancel-button
            @confirm="memberConfirm()"
            >
            <van-panel>
                <div slot="header">
                    <van-search placeholder="请输入会员名称" background="#D9D9D9" class="van-hairline--surround" v-model="searchInputName" @input="memberSearch()"/>
                </div>
                <div>
                    <van-picker :columns="columns" ref="memberPick"/>
                </div>
            </van-panel>
         </van-dialog>
    </div>
</template>

<script>
import {Toast, Sku, Dialog} from 'vant'
import {memberInfoListRequest, createOrderRequest} from '@/api/user'
export default {
    name:'productOrder',
    // 初始化一些数据
    mounted(){
    },
    data(){
        return {
            sku_show:false,
            productInfo:{
                 // 商品标题
                title: '测试商品',
                // 默认商品 sku 缩略图
                picture: '//img.yzcdn.cn/upload_files/2017/07/02/af5b9f44deaeb68000d7e4a711160c53.jpg',
                productId:'',
                id:0,
                price:0,
            },
            productInfoSku:{
                tree:[
                ],
                // 所有sku组合列表  红色、M码为一个sku组合
                list:[
                ],
                price: '1.00', // 默认价格（单位元）
                stock_num: 0, // 商品总库存
                collection_id: 2261, // 无规格商品 skuId 取 collection_id，否则取所选 sku 组合对应的 id
                none_sku: true, // 是否无规格商品
                hide_stock: false // 是否隐藏剩余库存
            },
            // 自定义步进器
            customStepperConfig:{
                // 处理剩余显示
                stockFormatter: (stock) => {
                    stock -= this.orderProductAmount;
                    return `剩余${stock}件`
                    },
                handleOverLimit: (data) => {
                    const { action, limitType, quota } = data;
                    const {LIMIT_TYPE} = Sku.skuConstants;
                    if (action === 'minus') {
                        Toast.fail('至少选择一件商品');
                    } else if (action === 'plus') {
                        if (limitType === LIMIT_TYPE.QUOTA_LIMIT) {
                        Toast.fail(`限购${quota}件`);
                        } else {
                            Toast.fail('库存不够了');
                        }
                    }  
                },
                handleStepperChange:(data) => {
                    if(data >= this.productAmount){
                        Toast.fail('所选商品数量不得大于库存数量!')
                        this.disableStepperInput = true;
                        this.orderProductAmount = this.productAmount;
                        return;                        
                    } 
                    this.disableStepperInput = false;
                    this.orderProductAmount = data;
                    this.orderPay = (this.productInfo.price) * (this.orderProductAmount) * 100;
                }
            },
            // 订单商品数量
            orderProductAmount:1,
            // 订单金额
            orderPay:0,
            // 商品数量
            productAmount:0,
            // 是否禁用步进器输入
            disableStepperInput:false,
            // 控制按钮显示
            isCartShow:false,
            // 选择会员编号弹框
            chosenMemberIdShow:false,
            searchInputName:'',
            // 会员选择框数组
            columns:[],
            // 会员选择项
            memberId:''
        }
    },
    methods:{
        
        // 展示购物车
        cartShowDetail(e){
            this.sku_show = true;
            this.isCartShow = true;
            // 初始化商品数据
            this.productInfo.title = e.productName;
            this.productInfo.picture = e.productImgSrc;
            this.productInfo.productId = e.productId;
            this.productInfo.id = e.id;
            this.productInfo.price = e.price;
            this.productAmount = e.productAmount;
            this.productInfoSku.stock_num = e.productAmount;
            this.orderPay = (this.orderProductAmount) * (this.productInfo.price) * 100
            this.memberId = '';
        },
        // 直接下单
        orderShowDetail(e){
            this.sku_show = true;
            this.isCartShow = false;
            // 初始化商品数据
            this.productInfo.title = e.productName;
            this.productInfo.picture = e.productImgSrc;
            this.productInfo.productId = e.productId;
            this.productInfo.id = e.id;
            this.productInfo.price = e.price;
            this.productAmount = e.productAmount;
            this.productInfoSku.stock_num = e.productAmount;
            this.orderPay = (this.orderProductAmount) * (this.productInfo.price)* 100
            this.memberId = '';
        },
        // 添加购物车
        productAddCart(){
            if(null == this.memberId || '' === this.memberId){
                Toast.fail('请输入会员编号！');
                return;
            } 
        },
        // 购买
        productBuy(){
            if(null == this.memberId || '' === this.memberId){
                Toast.fail('请输入会员编号！');
                return;
            } 
            // 参数封装
            let params = {
                id:this.productInfo.id,
                productId:this.productInfo.productId,
                price: this.productInfo.price,
                orderAmount:this.orderProductAmount,
                orderPay:(this.orderPay)/100,
                memberId:this.memberId
            }
            // 请求后台创建订单接口
            createOrderRequest(params).then(data => {
                const {resCode, resMsg} = data;
                if('000000' !== resCode){
                    Toast.fail(resMsg);
                    return;
                }
                Toast.success('下单成功');
                this.sku_show = false;
            })
        },
        // 数量发生变化
        stepperChange(value){
        },
        // 选择会员编号
        chosenMemberId(){
            this.chosenMemberIdShow = true;
            // 初始化会员列表
            memberInfoListRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail(resMsg);
                    return;
                }
                // 初始化会员列表
                result.map((val, index) => {
                    let memberItem = new Object();
                    memberItem.text = val.memberName;
                    memberItem.memberId = val.memberId;
                    this.$set(this.columns, index, memberItem)
                })
            })
        },
        // 根据会员名称搜索会员
        memberSearch(){
            memberInfoListRequest({memberName:this.searchInputName}).then(data => {
                this.columns = [];
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail(resMsg);
                    return;
                }
                // 初始化会员列表
                result.map((val, index) => {
                    let memberItem = new Object();
                    memberItem.text = val.memberName;
                    memberItem.memberId = val.memberId;
                    this.$set(this.columns, index, memberItem)
                })
            })
        },
        // 确认选择
        memberConfirm(){
            // 获得选中的值
            let memberItem = this.$refs.memberPick.getValues()[0];
            this.memberId = memberItem.memberId;
        },
    }
}
</script>

<style>

    .detail-sku{
       padding: 0 15px;  
    }
    .van-sku__stock{
        float: left
    }
</style>