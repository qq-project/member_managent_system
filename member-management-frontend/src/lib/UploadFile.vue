<template>
  <div>
    <div class="fileCon">
      <div class="list">
        <div v-for="(item,index) in list" :key="index">
          <div class="close" @click="close(index)"></div>
          <img :src="item" />
        </div>
        <div class="add" v-show="maxStatus" @click="chooseType">
          <div class="add-image"></div>
        </div>
      </div>
    </div>
    <input id="upload_file" type="file" class="file-input" accept="image/png,image/jpeg,image/jpg" :multiple="multiple" @change="inputChange" style="display: none" />
  </div>
</template>
<script>
import {fileUploadRequest} from '../api/user'
import {MessageBox} from 'mint-ui';
export default {
  data() {
    return {
      maxStatus: true,
      file:Object
    };
  },
  props: {
    multiple: Boolean,
    max: Number,
    list: Array,
  },
  components: {},
  mounted() {},
  methods: {
    chooseType() {
      document.getElementById("upload_file").click();
    },
    close(index) {
      this.list.splice(index, 1);
      this.maxStatus = this.list == this.max ? false : true;
    },
    inputChange : function(e){
      // 获取当前上传文件
      let files = e.target.files;
      this.file = files[0];
      //this.upload(files[0], '/api/upload/userAvartarUpload');
      // 上传操作
      /*      
      let uploadAll = [].slice.call(files ,0).map(this.upload);
      this.$ui.loading.open({//上传中效果，可自行替换。
        text: "上传中...",
        spinnerType: "fading-circle"
      });
      let result = await Promise.all(uploadAll);
      document.getElementById("upload_file").value = "";
      this.$ui.loading.close();
      */
    },
    upload(file, url) {
      const params = new FormData();
      params.append('avartar',file);
      fileUploadRequest(params, url).then(data => {
        const {resCode, resMsg, result} = data;
        // 上传失败
        if('000000' !== resCode){
          MessageBox.alert(resMsg);
          return;
        }
        // 成功处理

        return;
      })
      /*
      return new Promise(async (resolve, reject) => {
          let form = new FormData();
        form.append("file", file);
        form.append("***");//根据上传入参添加参数
        let result = await this.post("/file/upload-file", form);
        if (result.cd != 0) {//失败处理
          this.$ui.toast(`第${index + 1}张(${file.name})上传出错(已忽略)`);
          resolve(result);
          return;
        }
        this.list.push(result.data.url);
        if (this.list.length == this.max) {
          this.maxStatus = false;
        }
        resolve(result);
        
      });
      */

    }
  }
};
</script>
 
 
 <style lang="scss" scoped>
.fileCon {
  width: 100%;
  min-height: 76px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  .list {
    width: 100%;
    min-height: 76px;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    & > div {
      width: 50px;
      height: 50px;
      margin: 10px 10px 10px 0;
      position: relative;
      & > img {
        width: 100%;
        height: 100%;
      }
      .close {
        width: 15px;
        height: 15px;
        background-image: url(/images/icon_close.png);
        background-size: 100%;
        position: absolute;
        top: -7px;
        right: -7px;
      }
    }
  }
}
.add-image {
  width: 50px;
  height: 50px;
  background-size: 100%;
  background-image: url(/static/images/upload_avartar.png)
}
</style>