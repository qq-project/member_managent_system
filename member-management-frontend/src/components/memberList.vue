<!-- 会员列表展示页-->
<template>
    <div id="memberList" style="margin-top:-10px">
    <mt-header fixed title="会员列表" style="height:50px">
      <img class="add-person" src="../assets/add-person.png" slot="right" @click="addPerson"/>
    </mt-header>
    <van-search 
      placeholder="请输入会员名称" 
      shape="round" 
      background="#C6E2FF"
      v-model="searchName"
      @search="searchMember()"
      @cancel="searchConcel()" 
      @input="searchInput()"/> 
      <!--会员信息展示卡片--> 
      <van-contact-card
        v-for="item in memberInfoList"
        :key="item.id"
        type="edit"
        :name="item.memberName"
        :tel="item.memberPhone"
        @click="editMember(item)"/>

      <!-- 弹出层使用 编辑-->
      <van-popup v-model="popupShow"
       position="right"
       style="width:90%;height:auto;border-radius: 3%;"
       :close-on-click-overlay="false">
        <van-panel :title="popupTitle" icon="info">
          <div slot="header" class="edit-pannel-header">
            <van-nav-bar
              title="会员详情"
              right-text="删除"
              :left-arrow="false"
              @click-right="cancelEditMember()"
            >
              <van-icon name="clear" slot="right" color="red" size="20px"/>
            </van-nav-bar>
          </div>
          <van-field label="会员ID" size="large" name="memberId" v-model="currentMemberInfo.memberId" left-icon="ascending" disabled/>
          <van-field label="姓名" size="large" v-model="currentMemberInfo.memberName" left-icon="manager" />
          <van-field label="电话号码" size="large" v-model="currentMemberInfo.memberPhone" left-icon="phone"/>
          <van-radio-group class="van-radio" v-model="currentMemberInfo.gender">
            &nbsp;&nbsp;&nbsp;
            <van-icon name="friends"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label style="color:#323233;font-size:16px" disabled>性别</label>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
            <van-radio name="1" style="font-size:16px">男</van-radio>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <van-radio name="2" style="font-size:14px">女</van-radio>
            <br/><br/><br/>
          </van-radio-group>
          <van-field label="会员邮箱" size="large"  left-icon="chat" v-model="currentMemberInfo.memberEmail"/>
          <van-field label="会员生日" size="large" left-icon="clock"  v-model="currentMemberInfo.birthday" readonly @click="dateHandle()"/>
          <div class="integral-div">
            &nbsp;&nbsp;&nbsp;
            <span >
              <van-icon name="gem"/>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积分
            </span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <van-stepper
              v-model="currentMemberInfo.integral"
              integer
              :min="0"
              :step="10"
              input-width="50px"
            />
          </div>  
          <!--
          <van-field label="地址" size="large" :value="currentMemberInfo.address" left-icon="location"/>
          -->
          <div slot="footer">
              <van-button @click="updateMemberInfo()" type="info" round>保存</van-button>
              <van-button @click="deleteMemberInfo()" type="danger" round>删除</van-button>
          </div>
        </van-panel>
      </van-popup>
      <!--新增侧滑框-->
      <van-popup v-model="addPopupShow"
       position="left"
       style="width:90%;height:auto;border-radius: 3%;">
        <van-panel :title="popupTitle" icon="add">
        <van-field label="姓名" size="large" left-icon="manager" v-model="addMemberInfo.memberName"/>
        <van-field label="电话号码" size="large"  left-icon="phone" v-model="addMemberInfo.memberPhone"/>
        <van-radio-group class="van-radio" v-model="addMemberInfo.gender">
          &nbsp;&nbsp;&nbsp;
          <van-icon name="friends"/>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label style="color:#323233;font-size:16px" disabled>性别</label>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
          <van-radio name="1" style="font-size:16px">男</van-radio>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <van-radio name="2" style="font-size:14px">女</van-radio>
          <br/><br/><br/>
        </van-radio-group>
        <van-field label="会员邮箱" size="large"  left-icon="chat" v-model="addMemberInfo.memberEmail"/>
        <br/>
        <div class="integral-div">
          &nbsp;&nbsp;&nbsp;
          <span >
            <van-icon name="gem"/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;积分
          </span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <van-stepper
            v-model="addMemberInfo.integral"
            integer
            :min="0"
            :step="10"
            input-width="50px"
          />
        </div>
        <br/>
        <van-field label="会员生日" size="large" left-icon="clock"  v-model="addMemberInfo.birthday" readonly @click="dateHandle()"/>
        <!--
        <van-field label="地址" size="large" :value="currentMemberInfo.address" left-icon="location"/>
        -->
        <div slot="footer">
             <van-button @click="saveMemberInfo()" type="info" round>新增</van-button>
            <van-button @click="addPopupShow = false" round>取消</van-button>
        </div>
        </van-panel>
      </van-popup>
      <!--日期选择弹出框-->
      <van-dialog
        v-model="dateShow"
        title="选择日期"
        show-concel-button
        :close-on-click-overlay="false"
        @confirm="dateConfirm()">
        <van-datetime-picker
          type="date"
          :show-toolbar="false"
          v-model="currentDate"
          :formatter="dateFormatter"
          :max-date="maxDate"
          :min-date="minDate"/>
      </van-dialog>
    <tabbar :selected="selected"></tabbar>
    </div>
</template>

<script>
import {AddressList, Dialog, Toast, Popup} from 'vant';
import {MessageBox} from 'mint-ui'
import tabbar from './tabbar';
import {memberInfoListRequest, updateMemberInfoRequest, saveMemberInfoRequest, deleteMemberInfoRequest} from '@/api/user'
import moment from 'moment'
export default {
    mounted (){
      // 初始化列表信息
     this.initProfile();
    },
    data(){
         return {
            memberInfoList:[],
            currentMemberInfo:{},
            addMemberInfo:{
              memberName:'',
              memberPhone:'',
              birthday:'',
              memberEmail:'',
              integral:'0',
              gender:'1'
            },
            popupShow:false,
            addPopupShow:false,
            dateShow:false,
            popupTitle:'',
            selected: 'member',
            currentDate:new Date(),
            maxDate:new Date(),
            minDate:new Date(1900, 1 ,1),
            searchName:''
        };
    },
    methods:{
       // 初始化 memberInfoList
        initProfile(){
          memberInfoListRequest({}).then(data => {
          // 调用初始化赋值方法
            const {resCode, resMsg, result} = data;
            if('000000' != resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            // 处理会员生日
            this.memberInfoList = result;

          })
        },
        // 点击会员编辑
        editMember(item){
            this.popupTitle = "会员详情"
            this.popupShow = true;
            this.currentMemberInfo = item;
            this.currentMemberInfo.birthday = moment(item.birthday).format('YYYY-MM-DD')
            
        },
        // 关闭会员保存
        cancelEditMember(){
          this.popupShow = false;
          this.initProfile();
        },
        // 保存编辑的功能
        updateMemberInfo(){
          // 校验修改的信息
          const {id,memberName, memberPhone, gender, integral, address} = this.currentMemberInfo;
          // 用户名不能为空
          if(null == memberName || '' === memberName){
            Toast.fail('会员名不能为空')
            return;
          }
          // 正则校验，只支持中文
          if(!/^[\u4e00-\u9fa5]{1,10}$/.test(memberName)){
            Toast.fail('昵称只支持10以内中文字符')
            return;
          }
         // 校验手机号
          if(null == memberPhone || '' === memberPhone){
            Toast.fail('手机号不能为空')
            return;
          }
          if(!/^1[34578]\d{9}$/.test(memberPhone)){
            Toast.fail('请输入正确的手机号')
            return;
          }
          // 校验积分 不能为空或负
          if(null == integral || '' === integral){
            Toast.fail('积分不能为空')
            return;
          }
          if(integral < 0){
            Toast.fail('积分不能为负数')
            return;
          }
          // 请求后台
          updateMemberInfoRequest(this.currentMemberInfo).then(data => {
            // 处理响应
            const {resCode, resMsg} = data;
            // 请求失败
            if('000000' !== resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            Toast.success('保存成功！')
            // 请求成功，应该重新刷新数据，返回到列表
            this.initProfile();
          this.popupShow = false;
          })
        },
        // 删除会员
        deleteMemberInfo(){
          // 提示用户是否确认关闭
         Dialog.confirm({
           message:'确认删除？'
         })
         .then(() => {
           this.popupShow = false;
           deleteMemberInfoRequest(this.currentMemberInfo.id);
           this.initProfile();
         })
         .catch(()=> {
           this.popupShow = true;
         })
          
               
        },
        // 点击添加会员按钮
        addPerson(){
            this.addPopupShow = true;
            this.popupTitle = "添加会员";
            // 初始化 addMemberInfo
            this.addMemberInfo = {
              memberName:'',
              memberPhone:'',
              birthday:'',
              memberEmail:'',
              integral:'0',
              gender:'1'
            }
        },
        // 日期弹出
        dateHandle(){
          this.dateShow = true;
        },
        // 日期格式化
        dateFormatter(type, value){
          if (type === 'year') {
            return `${value}年`;
          } else if (type === 'month') {
            return `${value}月`
          } else if (type === 'day') {
            return `${value}日`
          }
        },
        // 确认选择日期
        dateConfirm(){
          this.addMemberInfo.birthday = moment(this.currentDate).format('YYYY-MM-DD')
          this.currentMemberInfo.birthday = moment(this.currentDate).format('YYYY-MM-DD')
        },
        // 添加会员
        saveMemberInfo(){
          // 校验用户输入
          const {memberName, memberPhone, memberEmail, integral, birthday } = this.addMemberInfo;
          // 用户名不能为空
          if(null == memberName || '' === memberName){
            Toast.fail('会员名不能为空')
            return;
          }
          // 正则校验，只支持中文
          if(!/^[\u4e00-\u9fa5]{1,10}$/.test(memberName)){
            Toast.fail('昵称只支持10以内中文字符')
            return;
          }
         // 校验手机号
          if(null == memberPhone || '' === memberPhone){
            Toast.fail('手机号不能为空')
            return;
          }
          if(!/^1[34578]\d{9}$/.test(memberPhone)){
            Toast.fail('请输入正确的手机号')
            return;
          }
          // 校验手机号
          if(null == memberEmail || '' === memberEmail){
            Toast.fail('邮箱不能为空')
            return;
          }
          // 正则先不写
          // 校验积分 不能为空或负
          if(null == integral || '' === integral){
            Toast.fail('积分不能为空')
            return;
          }
          if(null == birthday || '' === integral){
            Toast.fail('日期不能为空')
            return;
          }
          saveMemberInfoRequest(this.addMemberInfo).then(data => {
            const {resCode, resMsg} = data;
            if('000000' !== resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            // 刷新列表
            Toast.success('新增成功');
            this.initProfile();
            this.addPopupShow = false;
          })
        },
        // 搜索会员
        searchMember(){
          // 根据姓名模糊搜索
           memberInfoListRequest({memberName:this.searchName}).then(data => {
            const {resCode, resMsg, result} = data;
            if('000000' != resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            this.memberInfoList = result;
           });
        },
        // 取消搜索
        searchConcel(){
          console.log(111);
          memberInfoListRequest({}).then(data => {
            const {resCode, resMsg, result} = data;
            if('000000' != resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            this.memberInfoList = result;
           });
        },
        // 输入框变化时间
        searchInput(){
          // 根据姓名模糊搜索
           memberInfoListRequest({memberName:this.searchName}).then(data => {
            const {resCode, resMsg, result} = data;
            if('000000' != resCode){
              Dialog.alert({
                message:resMsg
              })
              return;
            }
            this.memberInfoList = result;
           });
        }
    },
    components:{
        'tabbar':tabbar
    }
}
</script>

<style>
#memberList .top {
  top: 40px;
  width: 100%;
  z-index: 2;
}

#memberList .add-person {
  width: 30px;
  height: 30px;
}
.van-radio {
  display: flex;
  align-items:center;
}
/* 步进器样式*/
.integral-div{
  display: flex;
  align-items:center;
}
.edit-pannel-header{
  align-items:center;
  height:auto;
}
</style>