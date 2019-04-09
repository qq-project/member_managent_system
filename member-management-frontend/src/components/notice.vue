<template>
 <div id="homePage">
    <mt-header fixed title="推广管理"></mt-header>
   
    <div class="top">
      <mt-search
        v-model="key"
        cancel-text="取消"
        placeholder="搜索">
      </mt-search>
    </div>

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
        selected: 'notice'
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
