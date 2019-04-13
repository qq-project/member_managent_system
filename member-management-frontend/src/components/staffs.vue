<template>
 <div id="noticePage">
    <mt-header fixed title="推广管理"></mt-header>
   
    <div class="top">
      <mt-search
        v-model="key"
        cancel-text="取消"
        placeholder="通过信息关键字搜索">
      </mt-search>
    </div>

    <div class="wait">
      <p>待发送：</p>
    </div>

    <div class="send">
      <p>已发送：</p>

    </div>

  <!-- <tabbar :selected="selected"></tabbar> -->
 </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem } from 'mint-ui';
import tabbar from './tabbar';
  export default{
    components: {tabbar},
    data(){
      return {
        key: '',
        // selected: 'notice'
      }
    },
    mounted () {

    },
    methods: {
      
    }
  }
</script>
<style>
.top {
  position: fixed;
  width: 100%;
}
</style>
