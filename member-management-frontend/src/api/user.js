/**
 * 该文件主要是封装用户的请求
 */

import {postRequest, getRequest, uploadRequest} from '../utils/request'

/**
 * 登录请求
 * @param {*} params 
 */
export const loginRequest = params => {
    return postRequest('/api/user/login', params).then(data => {
        return data;
    });
}

/**
 * 
 * @param {注册} params 
 */
export const registerRequest = params => {
    return postRequest('/api/register/userRegister', params).then(data => {
        return data;
    });
}

/**
 *  请求会员信息列表
 * @param {*} params 
 */
export const memberInfoListRequest = params => {
    return postRequest('/api/memberInfo/memberInfoList', params).then(data => {
        return data;
    });
}
/**
 * 请求当前登录用户信息
 * @param {*} params 
 */
export const userInfoRequest =() => {
    // 请求当前登录用户
    return postRequest('api/user/queryUserInfo', "").then(data =>{
        return data;
    });
}

/**
 *  登出
 * @param {*} params 
 */
export const logoutRequest = () => {
    return getRequest('/api/user/logout')
}


/**
 * 文件上传请求
 * params 文件内容
 * url 当前路径
 * 目前支持单文件上传
 */
export const fileUploadRequest=(file, url, filename) => {
    const params = new FormData();
    params.append(filename, file);
    return uploadRequest(url, params).then(data =>{
        return data;
    });
}