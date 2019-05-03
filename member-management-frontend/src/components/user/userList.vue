<template>
    <div id="staffPage">
        <mt-header fixed title="员工管理" style="height:50px">
        <router-link to="./usercenter" slot="left">
            <mt-button icon="back">返回</mt-button>
        </router-link>
        <div slot="right">
            <mt-button @click="editUserList()">编辑</mt-button>
        </div>
        </mt-header>
        <div class="userList">
            <van-address-list 
                v-model="chosenAddressId" 
                :list="list"
                :disabled-list="disabledList" 
                disabled-text="当前登录用户"
                :switchable="switchableShow"
                 @edit="editUser">
                <div class= "btn-del-list" v-if="delShow">
                    <br/><br/><br/><br/>
                    <van-button class="btn-del" type="danger" @click="delUser()">删除</van-button>
                </div>
            </van-address-list>
        </div>
        <tabbar :selected="selected"></tabbar>
        <userDetail ref="userDetail"></userDetail>
    </div>
</template>

<script>
import tabbar from "@/components/tabbar";
import userDetail from '@/components/user/userDetail'
import {userListRequest, userDelRequest} from '@/api/user'
import {Toast, Dialog} from 'vant'
export default {
    mounted(){
        this.initProfile();
    },
    data(){
        return{
            selected:'usercenter',
            chosenAddressId: '1',
            list: [],
            disabledList:[],
            // 显示选择
            switchableShow:false,
            // 删除按钮显示
            delShow:false
        }
    },
    methods:{
        // 初始化列表
        initProfile(){
            userListRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' !== resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                this.list = result.userListVoList;
                this.disabledList = result.userListDisabledVoList;
                this.chosenAddressId = this.list[0].id;
            })
        },
        // 编辑用户信息
        editUser(item, index){  
            this.$refs.userDetail.showUserDetail(item);
        },
        // 编辑列表按钮
        editUserList(){
            if(!this.switchableShow){
              this.switchableShow = true;  
              this.delShow = true;
            } else {
                this.switchableShow = false;
                this.delShow = false;
            }
        },
        // 删除用户信息
        delUser(){
            if(null == this.chosenAddressId){
                Dialog.alert({
                    message:'请选择要删除的用户！',
                })
                return;
            }
            // 删除操作
            userDelRequest({id:this.chosenAddressId}).then(data => {
                const {resCode, resMsg} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1000
                    })
                    return;
                }
                Toast.success({
                    message:'删除成功！',
                    duration:1000
                })
                this.initProfile();
            })
        }

    },
    components: {
        tabbar,
        userDetail:userDetail
        },
}
</script>

<style>
 .add-person {
  width: 30px;
  height: 30px;
}
.userList{
    margin-top:60px
}
.van-address-list__add{
    display: none
}
.btn-del{
    width:80%
}
</style>
