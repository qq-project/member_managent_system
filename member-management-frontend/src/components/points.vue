<template>
 <div id="pointsPage">
    <mt-header fixed title="积分配置" style="height:50px">
      <router-link to="/usercenter" slot="left">
        <mt-button icon="back">返回</mt-button>
      </router-link>
      <div slot="right" v-if="bonusPoints || deductPoints">
        <van-icon name="success" size="20px" @click="saveIntegral()"/>
      </div>
    </mt-header>
    
    <div class="first">
      <p class="title" style="margin-top:20px">消费送积分</p>
      <mt-switch class="my-switch" v-model="bonusPoints" @change="bonusPointsChange()">编辑</mt-switch>
      <div class="my-content">
        <div class="my-input">
          <span>每消费</span>
          <input type="number" v-model="money" name="money" id="money" :readonly="sendIntegral">
          <span class="span-two">元</span>
        </div>
        <div class="my-input">
          <span>赠送</span>
          <input type="number" v-model="points" name="points" id="points" :readonly="sendIntegral">
          <span class="span-two">积分</span>
        </div>
      </div>
    </div>

    <div class="second">
      <p class="title">积分抵扣</p> 
      <mt-switch class="my-switch" v-model="deductPoints" @change="deductPointsChange()">编辑</mt-switch>
      <table class="my-table" align="center" border=1 bordercolor="#00ff99">
        <tbody>
          <tr>
            <td colspan="2" height="40" align="center">
                积分抵扣规则
              </td>
          </tr>
          <tr>
            <td width="50%" height="30">积分范围</td>
            <td width="50%" height="30">折扣数目(可配置) </td>
          </tr>

          <tr v-for="item in integralConfigList" :key="item.id">
            <td height="35">
              <span>
              <input class="rate-input-new" type="number" v-model="item.integralMinAmount" name="firstRate" :readonly="ruleIntegral">
              --
              <input class="rate-input-new" type="number" v-model="item.integralMaxAmount" name="firstRate" :readonly="ruleIntegral">
              </span>
            </td>
            <td height="35">
              <input class="rate-input" type="number" v-model="item.discount" name="firstRate" :readonly="ruleIntegral">
            </td>
          </tr>
           <td colspan="2" height="40" align="center" v-if="deductPoints">
                <van-button type="info" size="mini" round @click="addIntegralClick()">新增</van-button>
              </td>
          <tr>
            <td colspan="2" align="center" class="tips">注意： 积分抵扣规则设置或者重置之后，之前的所有订单数据并不会被重新计算，只对设置完成后的订单有效。</td>
          </tr>
        </tbody>
      </table>
       <van-dialog
            v-model="addIntegralShow"
            show-cancel-button
            title="添加商品信息"
            @confirm="addIntegralConfirm()"
            @concel="addIntegralConcel()"
            >
            <table class="my-table-new" align="center" border=1 bordercolor="#00ff99">
        <tbody>
          <tr>
            <td colspan="2" height="40" align="center">
                积分抵扣规则
              </td>
          </tr>
          <tr>
            <td width="50%" height="30">积分范围</td>
            <td width="50%" height="30">折扣数目(不得大于 {{ checkDiscount }}) </td>
          </tr>

          <tr>
            <td height="35">
              <span>
              <input class="rate-input-new" type="number" v-model="addIntegralMinAmount" name="firstRate" disabled>
              --
              <input class="rate-input-new" type="number" v-model="addIntegralMaxAmount" name="firstRate">
              </span>
            </td>
            <td height="35">
              <input class="rate-input" type="number" v-model="addDiscount" name="firstRate">
            </td>
          </tr>
        </tbody>
      </table>
       </van-dialog>
    </div>

    <div class="third">

    </div>

  <tabbar :selected="selected"></tabbar>
 </div>

</template>
<script>
import { Header, Switch  } from 'mint-ui';
import tabbar from './tabbar';
import {integralConfigListRequest, updateIntegralConfigRequest, getIntegralMaxAmountRequest, saveIntegralConfigRequest} from '@/api/user'
import { Toast } from 'vant';
import { duration } from 'moment';
  export default{
    components: {tabbar},
    data(){
      return {
        key: '',
        selected: '',
        bonusPoints: false, // 消费送积分
        deductPoints: false, //抵扣积分
        awardPoints: false, // 奖励积分
        money: 10,
        points: 1,
        // 积分配置信息列表
        integralConfigList:[],
        // input是否可编辑
        sendIntegral:true,
        ruleIntegral:true,
        addIntegralShow:false,
        addIntegralMinAmount:0,
        addIntegralMaxAmount:0,
        addDiscount:0.01,
        checkDiscount:0,
        rate:0
      }
    },
    mounted () {
      this.initProfile();
    },
    methods: {
      initProfile(){
        integralConfigListRequest({}).then(data => {
          const {resCode, resMsg, result} = data;
          if('000000' != resCode){
            Toast.fail({
              message:resMsg,
              duration:1500
            })
            return;
          }
          this.integralConfigList = result;
          this.money = 1 / this.integralConfigList[0].rate;
        })
        
      },
      // 消费送积分编辑
      bonusPointsChange(){
        this.initProfile();
        if(!this.bonusPoints){
          this.sendIntegral = true;
        } else {
          this.sendIntegral = false;
        }
      },
      // 抵扣规则编辑
      deductPointsChange(){
          this.initProfile();
        if(!this.deductPoints){
          this.ruleIntegral = true;
        } else {
          this.ruleIntegral = false;
        }
      },
      // 保存积分更改
      saveIntegral(){
        let rate = this.points / this.money;
        console.log(this.integralConfigList);
        updateIntegralConfigRequest({integralConfigList:this.integralConfigList, rate:rate}).then(data => {
          const {resCode, resMsg} = data;
          if('000000' != resCode){
            Toast.fail({
              message:resMsg,
              duration:1500
            })
            return;
          }
           Toast.success({
              message:'保存更改成功！',
              duration:1500
            })
          this.initProfile();
        })
      },
      // 新增弹框
      addIntegralClick(){
        this.addIntegralShow = true;
        // 查询数据库最大范围
        getIntegralMaxAmountRequest({}).then(data => {
          const {resCode, resMsg, result} = data;
          if('000000' != resCode){
            Toast.fail({
              message:resMsg,
              duration:1500
            })
            return;
          }
          this.addIntegralMinAmount = result.integralMaxAmount + 1;
          this.checkDiscount = result.discount;
        })
      },
      // 新增配置弹框确认
      addIntegralConfirm(){
        // 参数校验
        if(null == this.addIntegralMaxAmount){
          Toast.fail({
            message:'请输入积分最大值',
            duration:1500
          })
          return;
        }
        if(this.addIntegralMinAmount >= this.addIntegralMaxAmount){
          Toast.fail({
            message:'积分最大值不得小于或等于最小值',
            duration:1500
          })
          return;
        }
        if(null == this.addDiscount){
          Toast.fail({
            message:'折扣不得为空',
            duration:1500
          })
          return;
        }
        if(this.checkDiscount <= this.addDiscount){
          Toast.fail({
            message:'新增折扣值不得比：'+this.checkDiscount+'大',
            duration:1500
          })
          return;
        }
        // 新增操作
        saveIntegralConfigRequest({
          integralMinAmount:this.addIntegralMinAmount,
          integralMaxAmount:this.addIntegralMaxAmount,
          discount:this.addDiscount,
          rate:this.integralConfigList[0].rate,
        }).then(data =>{
          const {resCode, redMsg} = data;
          if('000000' != resCode){
            Toast.fail({
              message:resMsg,
              duration:1500
            })
            return;
          }
          Toast.success({
              message:'新增成功！',
              duration:1500
            })
          this.initProfile();
        })
      },
      addIntegralConcel(){
        this.addIntegralMinAmount = 0;
        this.addDiscount = 0;
        this.initProfile();
      }
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
#pointsPage .my-table-new {
  width: 95%;
  height: 150px;
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

#pointsPage .rate-input-new {
  border: none;
  height: 35px;
  line-height: 35px;
  padding-left: 20px;
  width: 25%;
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
