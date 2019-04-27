<template>
    <mt-tabbar fixed v-model="tabbar_selected">
      <mt-tab-item id="member">
        <img slot="icon" src="../assets/member.png">
        会员
      </mt-tab-item>
      <mt-tab-item id="productList">
        <img slot="icon" src="../assets/products.png">
        商品
      </mt-tab-item>
      <mt-tab-item id="cart">
        <img slot="icon" src="../assets/cart.png">
        购物车
      </mt-tab-item>
      <mt-tab-item id="usercenter">
        <img slot="icon" src="../assets/usercenter.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
</template>
<script>
export default {
    name:'tabbar',
    data(){
        return{
        //选中的tabbar值message为外面页面传入的值selected
            tabbar_selected: this.selected
        }
    },
    props:{
        selected: String
    },
    watch: {
        tabbar_selected: function (val, oldVal) {
            // 这里就可以通过 val 的值变更来确定去向
            switch(val){
                case 'member':
                    this.$router.push('/member');
                break;
                case 'productList':
                    this.$router.push('/productList');
                break;
                case 'cart':
                    this.$router.push('/cart/orderCart');
                break;
                case 'usercenter':
                    this.$router.push('/usercenter');
                break;
            }
        }
    },
}
</script>
