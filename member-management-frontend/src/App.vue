<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
* {
  padding: 0;
  margin: 0;
  }
  
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  width: 100%;
  height: 100%;
  /*
  background-image: url("https://qiqi-management.oss-cn-beijing.aliyuncs.com/data/gitama4.jpg?Expires=1555492958&OSSAccessKeyId=TMP.AQE3jHqt14RjwanqFyxdU3tng-gnftE5oh0hxMJyR4BjTpmoqzOHYSnpttf1MC4CFQDFjLkV0UFhICEeB7Pe2aOHc6ZNbwIVAPDSSLkIaKCWu7oOxYamBL2CX22a&Signature=GgmlOwcl3PDNddwSEm5sXz%2BMgIg%3D");
  */
}

.mint-header, .mint-search {
  height: 60px;
}

</style>
