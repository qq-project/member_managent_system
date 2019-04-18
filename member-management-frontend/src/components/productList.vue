<template>
     <div id="productsPage" style="width:100%;height:100%;margin-top:-10px">
        <mt-header fixed title="商品管理" style="height:50px">
             <img class="add-product" src="../assets/plusNew.png" slot="right" @click="addProductBtn()"/>
        </mt-header>
        <van-search 
            placeholder="请输入商品名称" 
            shape="round" 
            background="#C6E2FF"
       />  
       <!--商品卡片展示-->
        <van-card
            v-for="product in productInfoList"
            :key="product.id"
            :num="product.productAmount"
            :price="product.price"
            :tag="product.categoryName"
            :thumb="product.productImgSrc"
            style="text-align:left">
            <!--标题-->
            <div slot="title">
                <strong>{{product.productName}}</strong>
            </div>
            <!--描述-->
            <div slot="desc">
                {{product.describute}}
            </div>
            <div slot="tags" class="card__tags">
                <van-tag plain type="danger">{{ product.categoryName }}</van-tag>
            </div>
            <div slot="footer" class="card__footer">
                <van-button round size="mini">按钮1</van-button>
                <van-button round size="mini">按钮2</van-button>
            </div>
        </van-card>
        <tabbar :selected="selected"></tabbar>
        <!-- 新增商品弹框-->
        <van-dialog
            v-model="addProductInfoShow"
            show-cancel-button
            title="添加商品信息"
            @confirm="addProductInfoConfirm()"
            @concel="addProductInfoConcel()"
            :before-close="addProductInfoBeforeClose"
            >
            <van-field label="商品名称"  v-model="addProductInfo.productName" label-align="center" placeholder="请输入商品名称" left-icon="bag-o" size="large"/>
            <van-field label="商品类别" v-model="categoryNames" label-align="center" placeholder="请选择商品类别" left-icon="cluster-o" size="large" readonly @click="chooseCategory()" required/>
            <van-field label="商品定价"  type="password" placeholder="请输入定价" label-align="center" left-icon="balance-o" size="large"/> 
            <van-field label="商品数量" type="password" placeholder="请输入数量" label-align="center" left-icon="shopping-cart-o" size="large"/> 
            <van-field label="商品描述" placeholder="请输入商品描述" label-align="center" left-icon="comment-o" size="large"/> 
            <br/>
            <van-panel>
                <div slot="header" style="text-align:left">
                    <div class="add-upload-div">
                        &nbsp;&nbsp;&nbsp;
                        <van-icon name="photo-o"/>
                        &nbsp;&nbsp;&nbsp;
                        <label style="font-size:16px;color:#323233">&nbsp;上传商品图片</label>
                    </div>
                </div>
                <br/>
                <van-uploader :after-read="productUpload">
                    <div v-if="'' ===productSrc">
                        <img src="@/assets/products.png" style="width:80px;height:80px;" ref="uploadProduct"/>
                    </div>
                     <div v-if="productSrc">
                        <img :src="productSrc" style="width:80px;height:80px;" ref="uploadProduct"/>
                    </div>
                </van-uploader>
            </van-panel>
        </van-dialog>
        <!--商品类别弹框-->
        <van-dialog
            v-model="categoryShow"
            show-cancel-button
            @cancel="cancelCategory()"
            @confirm="confirmCategory()">
            <van-nav-bar  title="选择类别">
                <van-icon slot="right" name="add" size="20px" @click="addategoryInfo()"/>
            </van-nav-bar>
            <van-panel>
                <!--可选类别-->
                <div slot="header" style="text-align:left;font-size:14px;color:#323233">
                    <label>&nbsp;&nbsp;&nbsp;&nbsp;可选类别：</label>
                </div>
                <van-tag round 
                    v-for="tag in tags" 
                    :key="tag.id"
                    :type="tag.categoryType" 
                    @click="tagChoose(tag)"
                    size="medium"
                    style="margin-top:10px;margin-left:5px"
                >{{ tag.categoryName}}</van-tag>
                <!--已选类别-->
                <div slot="footer" style="text-align:left;font-size:14px;color:#323233">
                    <label>已选类别：</label>
                    <br/>
                    <van-tag round 
                        v-for="tag in tagsSelectd" 
                        :key="tag.id"
                        :type="tag.categoryType" 
                        @click="selectTagClick(tag)"
                        size="large"
                        style="margin-top:10px;margin-left:5px"
                    >{{ tag.categoryName}}</van-tag>
                </div>
            </van-panel>
        </van-dialog>
        <!--新增商品类型Dialog-->
        <van-dialog
            v-model="addCategoryInfoShow"
            show-cancel-button
            @confirm = "addategoryInfoConfirm()"
            @concel  ="addategoryInfoConcel()"
            :before-close="addProductInfoBeforeClose">
            <van-nav-bar title="新增类别"/>
            <van-field label="类别名称" label-align="center" placeholder="请输入类别名称" left-icon="envelop-o" v-model="addCategoryInfo.categoryName"/>
            <van-field label="类别描述" label-align="center" placeholder="请输入类别描述" left-icon="comment-o" v-model="addCategoryInfo.describute"/>
        </van-dialog>
     </div>
</template>

<script>
import tabbar from './tabbar';
import {productInfoListRequest, categoryInfoListRequest, addCategoryInfoRequest} from '@/api/user'
import {Dialog, Toast} from 'vant'
export default {
    // 页面加载初始化数据使用
    mounted(){
        this.initProfile();
    },
    data(){
        return {
            // 商品列表
            productInfoList:[],
            // 当前商品信息
            currentProductInfo:{},
            // 新增商品信息
            addProductInfo:{
                productName:'',
                // 标签支持多选
                categoryIdList:[],
                price:0,
                // 此属性保存在库存表中
                productAmount:'',
                describute:''
            }, 
            // 商品类别拼接
            categoryNames:'',
             // 可选类别列表   
            tags:[],
            // 已选类别列表
            tagsSelectd:[],
            // 新增类别信息
            addCategoryInfo:{
                categoryName:'',
                describute:'',
            },
            // 上传图片
            productSrc:'',
            // 展示添加商品弹出框
            addProductInfoShow:false,
            // 展示类别选择框
            categoryShow:false,
            // 展示添加类别框
            addCategoryInfoShow:false,
            // 用户是否进行图片上传
            isUploadSuccess:false,
            // 校验通过标志
            isChecked:false,
            selected:'productList',
        }
    },
    methods:{
        // 初始化商品记录列表
        initProfile(){
            // 查询商品列表
            productInfoListRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' !== resCode){
                    Dialog.alert({
                        message:resMsg
                    })
                    return;
                }
                // 赋值
                this.productInfoList = result;
                Toast.success('查询成功')
            })
        },
        // 初始化商品类别记录列表
        initCategoryList(){
            categoryInfoListRequest({}).then(data => {
                const {resCode, resMsg, result} = data;
                if('000000' !== resCode){
                    Dialog.alert({
                        message:resMsg
                    })
                    return;
                }
                this.tags = result;
                this.categoryShow = true;
            })
        },
        //选择商品类别
        chooseCategory(){
            // 主动查询商品类别
            this.initCategoryList();
        },
        // 点击新增商品按钮
        addProductBtn(){
            this.addProductInfo = {
                productName:'',
                // 标签支持多选
                categoryIdList:[],
                price:0,
                // 此属性保存在库存表中
                productAmount:'',
                describute:''
            }
            this.addProductInfoShow = true;
            this.categoryNames = ''; 
        },
        // 提交新增商品
        addProductInfoConfirm(){
            // 校验参数
            const {productName, price, productAmount,describute, categoryIdList} = this.addProductInfo;
            // 商品名称不能为空
            if(null == productName || '' === productName){
                Toast.fail('商品名称不能为空');
                this.addProductInfoShow = true;
                return;
            }
            // 商品类别不能为空
            if('' === this.categoryNames){
                Toast.fail('商品类别不能为空');
                return;
            }
            // 商品定价不能等于小于0
            if(price <= 0){
                Toast.fail('商品售价不能小于零');
                return;
            }
            // 这里需要注意，用户中心应该允许配置仓库大小
            if(productAmount <= 0){
                Toast.fail('商品数量不能小于零');
                return;
            }
            // 定义一个图片上传 isUploadSuccess
            if(!this.isUploadSuccess){
                Toast.fail('请先上传商品图片');
                return; 
            }
            isChecked = true;
        },
        // 取消新增商品
        addProductInfoConcel(){
            this.addProductInfo = {
                productName:'',
                // 标签支持多选
                categoryIdList:[],
                price:0,
                // 此属性保存在库存表中
                productAmount:'',
                describute:''
            }
            this.addProductInfoShow = false;
            this.categoryNames = '';
        },
        // 关闭新增按钮的时候，校验数据
        addProductInfoBeforeClose(action, done){
            if(action === 'confirm'){
                if(this.isChecked){
                    this.isChecked = false;
                    done()
                } else {
                    done(false)
                }
            } else {
                done();
            }
        },
        // 点击可选类别标签
        tagChoose(tag){
            if(this.tagsSelectd.length >= 5){
                Toast.fail('最多可选5个类别！')
                return;
            }
            this.tagsSelectd.push(tag);
            // 从可选列表中删除该类别
            this.tags.splice(this.tags.indexOf(tag), 1);
        },
        // 点击已选类别标签
        selectTagClick(tag){
            // 点击后从已选列表中删除
            this.tagsSelectd.splice(this.tagsSelectd.indexOf(tag), 1);
            // 可选列表新增该选项
            this.tags.push(tag);
        },
        // 确认选择类别
        confirmCategory(){
            this.categoryNames = '';
            // 表示用户未选择
            if(null == this.tagsSelectd || this.tagsSelectd.length == 0){
                Toast.fail('请选择商品类别')
                return;
            }
            // 处理 新增页显示
            if(this.tagsSelectd.length == 1){
                // 用户只选择一个
                this.categoryNames = this.tagsSelectd[0].categoryName;
            } else {
                this.tagsSelectd.map(item => {
                  this.categoryNames = item.categoryName + ',' + this.categoryNames;  
                })
            }   
            this.addProductInfo.categoryIdList = this.tagsSelectd;

        },
        // 取消选择类别
        cancelCategory(){
            this.tagsSelectd = [];
            this.categoryNames = '';
        }, 
         // 点击新增类别标签
        addategoryInfo(){
            this.addCategoryInfoShow = true;
            this.addcategoryInfo = {
                categoryName:'',
                describute:'',  
            }
        },
        //提交 新增商品类别
        addategoryInfoConfirm(){
            // 参数校验
            const {categoryName, describute} = this.addCategoryInfo;
            if(null == categoryName || '' === categoryName){
                Toast.fail('类别名不能为空');
                return;
            }
            if(!/^[\u4e00-\u9fa5]{1,6}$/.test(categoryName)){
                Toast.fail('类别名只支持6位以内中文字符');
                return;
            }
            if(null == describute || '' === describute){
                Toast.fail('描述不能为空');
                return;
            }
            // 请求后台
            addCategoryInfoRequest({'categoryName':categoryName, 'describute':describute}).then(data => {
                const {resCode, resMsg} = data;
                if('000000' != resCode){
                    Dialog.alert({
                        message:resMsg
                    })
                    return;
                }
                this.addCategoryInfoShow = false;
                Toast.success('新增标签成功！')
                // 刷新标签列表
                this.initCategoryList();
            })
            this.isChecked = true;
        },
        // 取消新增
        addategoryInfoConcel(){},
        // 图片上传
        productUpload(){}
    },
    components:{
        'tabbar':tabbar
    }
}
</script>

<style>
  .card__footer {
    padding-top: 10px;
  }
  .van-tag {
    margin-right: 5px;
  }
  .add-product {
  width: 30px;
  height: 30px;
}

.add-upload-div{
    display: flex;
    align-items:center;
}
</style>
