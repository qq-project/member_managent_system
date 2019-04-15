<template>
  <div id="productsPage">
    <mt-header fixed title="商品管理"></mt-header>

    <div class="top">
      <div class="types">
        <p>&nbsp;商品类型</p>
        <img src="../assets/edit.png" alt="编辑"><!-- 是否不需要编辑 -->
      </div>
      <div class="typeItems">
        <div class="typeItem"
          :key="typeInfo.typeId"
          v-for="typeInfo in typeInfos">
          <div class="item" @click="chooseType(typeInfo.typeId)">
            <img :src="typeInfo.typePhoto" alt="商品类型">
            <p>{{typeInfo.typeName}}</p>
          </div>
        </div>
        <div class="typeItem" @click="addType">
          <img src="../assets/plus.png" alt="添加类型">
          <p>添加</p>
        </div>
      </div>
      <mt-search
        @click="search()"
        class="search"
        v-model="key"
        cancel-text="取消"
        placeholder="搜索">
      </mt-search>
      <div class="commodify-info">
        <div class="commodify-title">
          <span>商品图片</span>
          <span>商品名字</span>
          <span>价钱/￥</span>
          <span>商品数量</span>
        </div>
                <mt-cell-swipe
          :key="commodity.commodityId"
          v-for="commodity in commodities"
          :right="[
            {
              content: '删除',
              style: { background: 'red', color: '#fff', width: '80px' },
              handler: () => deleteOrderInfo(commodity.commodityId)
            }
          ]">
          <img class="commodityPhoto" :src="commodity.commodityPhoto" alt="商品图片">
          <p>{{commodity.commodityName}}</p>
          <p>{{commodity.value}}</p>
          <p>{{commodity.count}}</p>
          </mt-cell-swipe>
      </div>
    </div>


  <tabbar :selected="selected"></tabbar>
 </div>

</template>
<script>
import { Search, Cell, Header, Navbar, TabItem, Toast } from 'mint-ui';
import tabbar from './tabbar';
  export default{
    components: {tabbar},
    data(){
      return {
        selected: 'products',
        key: '',
        typeInfos: [],
        commodities: []
      }
    },
    mounted () {
      this.initTypeInfos();
      this.initCommodities();    },
    methods: {
      initCommodities() {
        this.commodities = [
          {
            commodityId: 1,
            commodityName: '2B铅笔',
            commodityPhoto: require('../assets/pencil.png'),
            value: 1.2,
            count: 200
          },
          {
            commodityId: 2,
            commodityName: '橡皮',
            commodityPhoto: require('../assets/rubber.png'),
            value: 1.2,
            count: 200
          },
          {
            commodityId: 3,
            commodityName: '尺子',
            commodityPhoto: require('../assets/ruler.png'),
            value: 1.2,
            count: 200
          },
          {
            commodityId: 4,
            commodityName: '2B铅笔',
            commodityPhoto: require('../assets/pencil.png'),
            value: 1.2,
            count: 200
          },
          {
            commodityId: 5,
            commodityName: '橡皮',
            commodityPhoto: require('../assets/rubber.png'),
            value: 1.2,
            count: 200
          },
          {
            commodityId: 6,
            commodityName: '尺子',
            commodityPhoto: require('../assets/ruler.png'),
            value: 1.2,
            count: 200
    }
        ]
      },
      initTypeInfos() {
        this.typeInfos = [ 
          {
            typeId: 1,
            typeName: '文具',
            typePhoto: require('../assets/defaultTypePhoto.png')
          },
          {
            typeId: 2,
            typeName: '包',
            typePhoto: require('../assets/bao.png')
          },
          {
            typeId: 3,
            typeName: '服饰',
            typePhoto: require('../assets/cloth.png')
          },
          {
            typeId: 4,
            typeName: '鞋子',
            typePhoto: require('../assets/shoes.png')
          },
          {
            typeId: 5,
            typeName: '水果',
            typePhoto: require('../assets/fruit.png')
          },
          {
            typeId: 6,
            typeName: '服饰',
            typePhoto: require('../assets/cloth.png')
          },
          {
            typeId: 7,
            typeName: '鞋子',
            typePhoto: require('../assets/shoes.png')
          },
          {
            typeId: 8,
            typeName: '水果',
            typePhoto: require('../assets/fruit.png')
  }
        ]
      },
      addType() {
        // TODO: 进入添加类型页面 
        Toast("已成功添加类型！");
      },
      chooseType(typeId) {
        Toast("已选择商品类型" + typeId);
      }
    }
  }
</script>
<style>
#productsPage {
  width: 100%;
  height: 100%;
}

#productsPage .mint-searchbar {
  background-color: #B9D3EE;
}

#productsPage .typeItems {
  background-color: #BFEFFF;
  ;
}

#productsPage .commodify-title {
  display: flex;
  justify-content: space-around;
  font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

#productsPage .commodify-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

#productsPage .mint-cell-value {
  width: 100%;
  justify-content: space-around;
}

#productsPage .mint-header {
  z-index: 2;
}

#productsPage .commodityPhoto {
  width: 30px;
  height: 30px;
}

#productsPage .top {
  position: relative;
  width: 100%;
  height: 120px;
  background-color: #e8e8e8;
}

#productsPage .typeItem {
  width: 40px;
  height: 80px;
  margin-left: 10px;
  margin-top: 15px;
  display: inline-block;
  font-family: cursive;
}

#productsPage .item {
  width: 40px;
  height: 80px;
}

#productsPage .typeItem img {
  width: 30px;
  height: 30px;
}

#productsPage .types {
  display: flex;
  justify-content: space-between;
  width: 100%;
  height: 40px;
  line-height: 40px;
  font-family: cursive;
  font-weight: bold;
  background-color: #fff;
}

#productsPage .types img {
  width: 40px;
  height: 40px;
}

</style>
