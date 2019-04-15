/**
 * 该文件主要是针对网络请求进行封装处理
 * 使用axios
 * 注意，跨域请求时，需要更改 config文件下的index.js文件  proxyTable: {}
 */
import axios from 'axios'
import {MessageBox} from 'mint-ui'
import doCookie from './cookieUtil'
axios.defaults.withCredentials = true
axios.defaults.headers.common['Authorization'] = doCookie.getCookie('SESSIONID')
/**
 * 针对请求进行统一拦截处理
 */
axios.interceptors.request.use(request => {
    return request;
},err =>{
    // 处理错误情况
    MessageBox.alert('请求超时！')
    return;
})

/**
 * 响应的统一处理
 */
    /**
     *  response 对象
     * data 服务器返回的数据
     * status: 200 服务器响应成功
     * statusText：
     * headers
     * config
     * 
     * 规定：
     *  后台返回数据 必须 包含 resCode 和 resMsg字段 
     *  resCode === '000000' 表示成功，其他全部失败
     */
    /**
axios.interceptors.response.use(response =>{

    if(response.status && response.status === 200 && response.data.resCode !== '000000'){
        MessageBox.alert('请求失败：'+response.data.resMsg)
        return;
    }
}, err => {
    if (err.response.status == 504 || err.response.status == 404) {
        MessageBox.alert('服务器被吃了⊙﹏⊙∥');
      } else if (err.response.status == 403) {
        MessageBox.alert('权限不足,请联系管理员!');
      } else if (err.response.status == 401) {
        MessageBox.alert(err.response.data.msg);
      } else {
        if (err.response.data.msg) {
            MessageBox.alert(err.response.data.msg);
        }else{
            MessageBox.alertr('未知错误!');
        }
    }
});
*/
/**
 * 常用请求配置
 */
let base = ''

/**
 * post 请求
 * @param {*} url 
 * @param {*} params 
 */
export const postRequest = (url, params) => {
    return axios({
        method:'post',
        url:`${base}${url}`,
        // 统一使用JSON格式
        data:JSON.stringify(params),
        headers:{
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).then((res) => {
        /**
         * 响应的一些处理
         * 规定：
         *  后台返回数据必须包含：
         *  resCode：'000000' 表示 成功
         *  resMsg:响应消息
         *  result: 响应结果
         * 
         *  response 对象
         *   data 服务器返回的数据
         *   status: 200 服务器响应成功
         *   statusText：
         *   headers
         *   config
         */
        /**
        const data = res.data;
        if(null == data) {
            MessageBox.alert('请求失败！');
        }
        if(data.resCode !== '000000'){
            MessageBox.alert(data.resMsg);
            return Promise.reject(data.resMsg)
        }
         */
        // 判断是否登录
        const {data} = res;
        if('300000' === data.resCode){
            console.log('未登录');
            // 将登录标志写进 localstorage中
            localStorage.setItem('token','unLogin');
        }
        return data;
    }, (err) => {
        console.log(err)
        return;
        // 响应错误  根据响应码提示用户
        if (err.response.status == 504 || err.response.status == 404) {
            MessageBox.alert('服务器被吃了⊙﹏⊙∥');
          } else if (err.response.status == 403) {
            MessageBox.alert('权限不足,请联系管理员!');
          } else if (err.response.status == 401) {
            MessageBox.alert(err.response.data.msg);
          } else {
            if (err.response.data.msg) {
                MessageBox.alert(err.response.data.msg);
            }else{
                MessageBox.alertr('未知错误!');
            }
        }
    })
}

/**
 * get 请求
 * @param {*} url 
 */
export const getRequest = (url) => {
    return axios({
        method:'get',
        url: `${base}${url}`
    })
}

export const uploadRequest = (url, params) => {
    return axios({
        method:'post',
        url: `${base}${url}`,
        data: params,
        headers:{
            'Content-Type': 'multipart/form-data' 
        }
    }).then(res => {
        return res.data;
    })
}