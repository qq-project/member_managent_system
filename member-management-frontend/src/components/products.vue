<template>
 <div id="homePage">
    <mt-header fixed title="商品管理"></mt-header>
   
      <mt-search class="top"
        v-model="key"
        cancel-text="取消"
        placeholder="搜索">
      </mt-search>
                <mt-cell-swipe
            :key="n"
            v-for="n in 105"
            >
          das
          </mt-cell-swipe>

  <tabbar :selected="selected"></tabbar>
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
        orderKey: '',
        selected: 'products'
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
