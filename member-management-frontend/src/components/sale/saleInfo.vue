<template>
    <div class="sale-info">
        <mt-header fixed title="销售管理" style="height:50px;z-index:100">    
        <router-link to="../usercenter" slot="left">
            <mt-button icon="back">返回</mt-button>
        </router-link>
        <div slot="right">
            <mt-button>编辑</mt-button>
        </div>
        </mt-header>
        <van-tabs type="line" style="margin-top:50px" @click="chartsTabClick">
            <van-tab title="商品销量图">
                 <ve-pie :data="chartDataPie" :settings="pieSettings"></ve-pie>
            </van-tab>
            <van-tab title="会员订单图">
                <ve-histogram :data="chartDataHistogram"></ve-histogram>
            </van-tab>
             <van-tab title="每日销售图">
                 <ve-line :data="chartDataLine"></ve-line>
            </van-tab>
        </van-tabs>
        
        <tabbar :selected="selected"></tabbar>
    </div> 
</template>

<script>
import tabbar from "@/components/tabbar";
import {saleInfoProductRequest, orderInfoProductRequest, orderInfoDayRequest} from '@/api/user'
import {Toast} from 'vant'
// 引入饼状图
import Vepie from 'v-charts/lib/pie.common'
// 引入饼状图
import Veline from 'v-charts/lib/line.common'
import Vehistogram from 'v-charts/lib/histogram.common'
export default {
    mounted(){
        //this.initProductInfo();
        this.initProductInfo();
        this.initMemberInfo();
        this.initOrderInfo();
    },
    data(){
        this.pieSettings = {
            radius: 80,
            offsetY: 200,
            legendLimit:5
        }
        return {
            selected:'usercenter',
            chartData: {
                columns: ['日期', '访问用户'],
                rows: [
                    { '日期': '1/1', '访问用户': 1393 },
                    { '日期': '1/2', '访问用户': 3530 },
                    { '日期': '1/3', '访问用户': 2923 },
                    { '日期': '1/4', '访问用户': 1723 },
                    { '日期': '1/5', '访问用户': 3792 },
                    { '日期': '1/6', '访问用户': 4593 }
                ]
            },
            chartDataPie:{
                columns:[],
                rows:[],
            },
            chartDataHistogram:{
                columns:[],
                rows:[], 
            },
            chartDataLine:{
                columns:[],
                rows:[], 
            }
        }
    },
    methods:{
        /*
        initProductInfo(){
            saleInfoProductRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                let legendData = [];
                let seriesData = [];
                result.map((val, index) => {
                    legendData[index] = val.productName;
                    seriesData[index] = {value:val.totalCount, name:legendData[index]}
                })
                let pieEcharts =echarts.init(document.getElementById('productInfo'));
                pieEcharts.setOption({
                     title: {
                        text: '商品销量前五展示',
                        bottom:'30%',
                        left:'28%'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        show: true,
                        orient: 'vertical',
                        left: 'left',
                        data: legendData
                    },
                    series : [
                        {
                            name: '销售详情',
                            type: 'pie',
                            radius : '50%',
                            center: ['50%', '40%'],
                            data:seriesData,
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });
            })
        },
        */
        initProductInfo(){
            saleInfoProductRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                let pieColumns = ['商品名称','销售数量'];
                let pieRows = [];
                result.map((val, index) => {
                   let productName = val.productName;
                   pieRows[index] = {'商品名称':productName, '销售数量':val.totalCount}
                })
                this.chartDataPie.columns = pieColumns;
                this.chartDataPie.rows = pieRows;
            })
            
        },
        // 初始化会员订单图
        initMemberInfo(){
           orderInfoProductRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                let lineColumns = ['会员名称','订单数量'];
                let lineRows = [];
                result.map((val, index) => {
                   let memberName = val.memberName;
                   lineRows[index] = {'会员名称':memberName, '订单数量':val.totalCount}
                })
                this.chartDataHistogram.columns = lineColumns;
                this.chartDataHistogram.rows = lineRows;
            })  
        },
        // 初始化 每日销售额
        initOrderInfo(){
            orderInfoDayRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' != resCode){
                    Toast.fail({
                        message:resMsg,
                        duration:1500
                    })
                    return;
                }
                let lineColumns = ['天','销售额'];
                let lineRows = [];
                result.map((val, index) => {
                   let memberName = val.days;
                   lineRows[index] = {'天':memberName, '销售额':val.totalAmount}
                })
                this.chartDataLine.columns = lineColumns;
                this.chartDataLine.rows = lineRows;
            }) 
        },
        // 切换tab
        chartsTabClick(index, title){
             
        }
    },
    components: {
        tabbar,
        've-pie':Vepie,
        've-line':Veline,
        've-histogram':Vehistogram
    },
}
</script>

<style>
.chart-div{
    margin-top:10px
}
</style>
