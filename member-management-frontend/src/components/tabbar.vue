<template>
    <mt-tabbar fixed v-model="tabbar_selected">
      <mt-tab-item id="member">
        <img slot="icon" src="../assets/member.png">
        会员
      </mt-tab-item>
      <mt-tab-item id="products">
        <img slot="icon" src="../assets/products.png">
        商品
      </mt-tab-item>
      <mt-tab-item id="notice">
        <img slot="icon" src="../assets/notice.png">
        推广
      </mt-tab-item>
      <mt-tab-item id="usercenter">
        <img slot="icon" src="../assets/usercenter.png">
        我的
      </mt-tab-item>
    </mt-tabbar>
</template>
<script>
import tabbar from './tabbar';
export default {
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
            console.log(val)
            switch(val){
                case 'member':
                    this.$router.push('/member');
                break;
                case 'products':
                    this.$router.push('/products');
                break;
                case 'notice':
                    this.$router.push('/notice');
                break;
                case 'usercenter':
                    this.$router.push('/usercenter');
                break;
            }
        }
    },
}
</script>
