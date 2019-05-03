<!--查看列表用户详情-->
<template>
    <div class="user-detail">
        <van-dialog v-model="detailShow" title="个人信息详情" close-on-click-overlay 
            @confirm="userDetailConfirm()" 
            @cancel="userDetailCancel()" 
            show-cancel-button
            confirm-button-text="保存">
            <br>
            <img :src="userInfo.imgSrc" style="width:80px;height:80px;border-radius: 50%;" ref="uploadAvartar"/>
            <van-field label="邮箱"  label-align="center" disabled left-icon="envelop-o" v-model="userInfo.email"/>
            <van-field label="昵称"  label-align="center" left-icon="user-o" v-model="userInfo.nickname" />
            <van-field label="手机号" label-align="center" left-icon="phone-circle-o" v-model="userInfo.mobile"/> 
            <br/>
            <van-radio-group class="van-radio" v-model="userInfo.gender" disabled>
                &nbsp;&nbsp;&nbsp;
                <label style="color:#323233;font-size:14px" disabled>性别</label>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
                <van-radio name="1" style="font-size:14px">男</van-radio>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <van-radio name="2" style="font-size:14px">女</van-radio>
            </van-radio-group>
            <br/>
            <van-field label="地址" label-align="center" left-icon="logistics" type="textarea" autosize v-model="userInfo.address" />
        </van-dialog>
    </div>
</template>

<script>
import {userDetailRequest, userUpdateRequest} from '@/api/user'
import {Toast} from 'vant'
import { duration } from 'moment';
export default {
    data(){
        return{
            userInfo:{},
            // 显示用户详情弹框
            detailShow:false,

        }
    },
    methods:{
        // 显示页面并初始化数据
        showUserDetail(item){
            const {id} = item;
            if(null == id || '' === id){
                Toast.fail({
                    message:'请求参数为空，查询失败！',
                    duration:1500
                })
                return;
            }
            userDetailRequest({id:id}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail({
                    message:resMsg,
                    duration:1500
                })
                    return; 
                }
                this.userInfo = result;
            })
            this.detailShow = true;
        },
        // 详情关闭
        userDetailCancel(){

        },
        // 详情确认
        userDetailConfirm(){
            const {nickname, mobile, address} = this.userInfo;
            if(null == nickname || '' === nickname){
                Toast.fail({
                    message:'用户名不能为空',
                    duration:1000
                })
                return;
            }
            if(null == mobile || '' === mobile){
                Toast.fail({
                    message:'手机号不能为空',
                    duration:1000
                })
                return;
            }
            // 请求后台保存
            userUpdateRequest(this.userInfo).then(data => {
                const {resCode, resMsg} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                Toast.success({
                    message:'保存：《'+nickname+'》信息成功！',
                    duration:1500
                })
                this.detailShow = false;
                // 刷新列表
                this.$parent.initProfile();
            })
        },
    }
}
</script>

<style>
/* radio flex布局*/
.van-radio {
  display: flex;
  align-items:center;
}
</style>
