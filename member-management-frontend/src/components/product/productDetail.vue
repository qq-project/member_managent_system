<template>
  <div class="goods">
    <div class="header-div">  
        <div class="left-icon-div">
            <van-nav-bar title="商品详情" class="van-nav">
                <van-icon name="arrow-left" slot="left" size="20px" color="#969799" @click="goBack()"/>
            </van-nav-bar>
        </div>
        <div class="swipe-div">
            <van-swipe class="goods-swipe" :autoplay="3000">
            <van-swipe-item v-for="thumb in imgSrc" :key="thumb">
                <img :src="thumb" class="thumb-img">
            </van-swipe-item>
            </van-swipe>
        </div>
    </div>
    <van-cell-group class="cell-group">
      <van-cell>
        <div class="goods-title">{{ productInfo.productName }}</div>
        <div class="goods-price">{{ formatPrice(productInfo.price) }}</div>
      </van-cell>
      <van-cell class="goods-express">
        <van-col span="10">运费：免运费</van-col>
        <van-col span="14">剩余：{{ productInfo.productAmount }}</van-col>
      </van-cell>
    </van-cell-group>

    <van-cell-group class="goods-cell-group">
      <van-cell value="进入店铺" icon="shop-o" is-link>
        <template slot="title">
          <span class="van-cell-text">有赞的店</span>
          <van-tag class="goods-tag" type="danger">官方</van-tag>
        </template>
      </van-cell>
      <van-cell title="线下门店" icon="location-o" is-link />
    </van-cell-group>
    <!--
    <van-cell-group class="goods-cell-group">
      <van-cell title="查看商品详情" is-link @click="sorry" />
    </van-cell-group>
    -->
    <van-goods-action>
      <van-goods-action-mini-btn icon="chat-o">
        客服
      </van-goods-action-mini-btn>
      <van-goods-action-mini-btn icon="cart-o">
        购物车
      </van-goods-action-mini-btn>
      <van-goods-action-big-btn @click="addCart()">
        加入购物车
      </van-goods-action-big-btn>
      <van-goods-action-big-btn primary @click="addOrder()">
        下单
      </van-goods-action-big-btn>
    </van-goods-action>
     <!--商品规格pannel-->
    <productOrder ref="mychild"></productOrder>
  </div>
</template>

<script>
import { Toast } from 'vant';
import productOrder from './productOrder'
export default {
  mounted(){
      // 获取数据
      this.productInfo = this.$route.query.productInfo;
      this.imgSrc.push(this.productInfo.productImgSrc);
  },
  components: {
      'productOrder':productOrder
  },
  data() {
    return {
      productInfo:{},
      // 图片数组
      imgSrc:[],
      goods: {
        title: '美国伽力果（约680g/3个）',
        price: 2680,
        express: '免运费',
        remain: 19,
        thumb: [
          'https://img.yzcdn.cn/public_files/2017/10/24/e5a5a02309a41f9f5def56684808d9ae.jpeg',
        ]
      }
    };
  },
  methods: {


    goBack(){
        // 返回上层
        this.$router.go(-1);
    },      
    formatPrice() {
      return '¥' + (this.productInfo.price * 1).toFixed(2);
    },
    // 购物车
    addCart(){
        this.$refs.mychild.cartShowDetail(this.productInfo);
    },
    // 直接购买
    addOrder(){
        this.$refs.mychild.orderShowDetail(this.productInfo);
    }
  }
};
</script>
<style>
    .goods{
        margin-top: 0px
    }
    .thumb-img{
        width:100%;
        height:320px;
    }
    .van-nav{}
</style>