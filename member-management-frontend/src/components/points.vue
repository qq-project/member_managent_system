<template>
 <div id="pointsPage">
    <mt-header fixed title="积分配置">
      <router-link to="/usercenter" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
    </mt-header>
    
    <div class="first">
      <p class="title">消费送积分</p>
      <mt-switch class="my-switch" v-model="bonusPoints">开启</mt-switch>
      <div class="my-content">
        <div class="my-input">
          <span>每消费</span>
          <input type="number" :value="money" name="money" id="money">
          <span class="span-two">元</span>
        </div>
        <div class="my-input">
          <span>赠送</span>
          <input type="number" :value="points" name="points" id="points">
          <span class="span-two">积分</span>
        </div>
      </div>
    </div>

    <div class="second">
      <p class="title">积分抵扣</p>
      <mt-switch class="my-switch" v-model="deductPoints">开启</mt-switch>
      <table class="my-table" align="center" border=1 bordercolor="#00ff99">
        <tbody>
          <tr>
            <td colspan="2" height="40" align="center">积分抵扣规则</td>
          </tr>
          <tr>
            <td width="50%" height="30">积分范围</td>
            <td width="50%" height="30">折扣数目(可配置) </td>
          </tr>
          <tr>
            <td height="35">1-100</td>
            <td height="35">
              <input class="rate-input" type="number" :value="firstRate" name="firstRate">
            </td>
          </tr>
          <tr>
            <td height="35">101-500</td>
            <td height="35">
              <input class="rate-input" type="number" :value="secondRate" name="secondRate">
            </td>
          </tr>
          <tr>
            <td height="35">501-2000</td>
            <td height="35">
              <input class="rate-input" type="number" :value="thirdRate" name="thirdRate">
            </td>
          </tr>
          <tr>
            <td height="35">2001-5000</td>
            <td height="35">
              <input class="rate-input" type="number" :value="forthRate" name="forthRate">
            </td>
          </tr>
          <tr>
            <td colspan="2" align="center" class="tips">注意： 积分抵扣规则设置或者重置之后，之前的所有订单数据并不会被重新计算，只对设置完成后的订单有效。</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="third">

    </div>

  <tabbar :selected="selected"></tabbar>
 </div>

</template>
<script>
import { Header, Switch  } from 'mint-ui';
import tabbar from './tabbar';
  export default{
    components: {tabbar},
    data(){
      return {
        key: '',
        selected: '',
        bonusPoints: false, // 消费送积分
        deductPoints: false, //抵扣积分
        awardPoints: false, // 奖励积分
        firstRate: 0.95,
        secondRate: 0.9,
        thirdRate: 0.85,
        forthRate: 0.8,
        money: 1,
        points: 1
      }
    },
    mounted () {

    },
    methods: {
      
    }
  }
</script>
<style>
#pointsPage {
  width: 100%;
}

#pointsPage .my-table {
  width: 95%;
  height: 300px;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  border-color: #8F8F8F;
  margin-left: 10px;
  margin-top: 20px;
}

#pointsPage .tips {
  padding: 10px;
}

#pointsPage .rate-input {
  border: none;
  height: 35px;
  line-height: 35px;
  padding-left: 20px;
  width: 40%;
  outline: none;
  font-size: 16px;
  color: blue;
}

#pointsPage .first {
  margin-bottom: 20px;
}

#pointsPage .second {
  margin-bottom: 120px;
}

#pointsPage .my-content {
  width: 100%;
  display: flex;
  justify-content: space-around;
}

#pointsPage .title {
  height: 60px;
  line-height: 60px;
  font-size: 18px;
  justify-content: flex-start;
  display: flex;
  padding-left: 10px;
  padding-top: 20px;
  font-weight: bold;
}

#pointsPage .my-switch {
  padding-left: 10px;
  width: 100px;
}

#pointsPage .my-input {
  border: 1px solid;
  display: flex;
  justify-content: space-around;
  width: 160px;
  height: 35px;
  border-radius: 10px;
  border-color: #8F8F8F;
  background-color: #EBEBEB;
  margin-top: 20px;
}

#pointsPage .my-input span:first-child {
  line-height: 35px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

#pointsPage .my-input .span-two {
  line-height: 35px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

#pointsPage .my-input input {
  border: none;
  width: 50px;
  outline: none;
  text-align: center;
  border-left: 1px solid;
  border-right: 1px solid;
  color: #8F8F8F;
  line-height: 55px;
  height: 35px;
  font-size: 16px;
}
</style>
