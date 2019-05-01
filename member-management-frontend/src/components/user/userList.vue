<template>
    <div id="staffPage">
        <mt-header fixed title="员工管理" style="height:50px">
        <router-link to="./usercenter" slot="left">
            <mt-button icon="back">返回</mt-button>
        </router-link>
        <img  class="add-person" src="@/assets/add-person.png" slot="right"/>
        </mt-header>
        <div class="userList">
            <van-address-list v-model="chosenAddressId" :list="list" :switchable="false">
            </van-address-list>
        </div>
        <tabbar :selected="selected"></tabbar>
    </div>
</template>

<script>
import tabbar from "@/components/tabbar";
import {userListRequest} from '@/api/user'
import {Toast} from 'vant'
export default {
    mounted(){
        this.initProfile();
    },
    data(){
        return{
            selected:'usercenter',
            chosenAddressId: '1',
            list: [],
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
                this.list = result;
            })
        }
    },
    components: {tabbar},
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
</style>
