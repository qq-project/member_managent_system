/**
 * 该文件主要是封装用户的请求
 */

import {postRequest, getRequest} from '../utils/request'
import TuaStorage from 'tua-storage'

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
 *  登出
 * @param {*} params 
 */
export const logoutRequest = () => {
    return getRequest('/api/user/logout')
}
