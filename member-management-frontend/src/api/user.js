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
 * 更新会员信息
 * @param {*} params 
 */
export const updateMemberInfoRequest = (params) => {
    return postRequest('/api/memberInfo/updateMemberInfo', params).then(data => {
        return data;
    });
}

/**
 * 保存会员信息
 * @param {*} params 
 */
export const saveMemberInfoRequest = (params) => {
    return postRequest('/api/memberInfo/saveMemberInfo', params).then(data =>{
        return data;
    })
}

/**
 * 删除会员
 * @param {*} params 
 */
export const deleteMemberInfoRequest = (params) =>{
    postRequest('/api/memberInfo/deleteMemberInfo', params).then(data => {
        return data;
    })
}

/**
 * 查询当前用户信息 
 */
export const userInfoRequest =() => {
    // 请求当前登录用户
    return postRequest('api/user/queryUserInfo', "").then(data =>{
        return data;
    });
}

/**
 * 更新当前用户信息
 * @param {*} params 
 */
export const saveUserInfoRequest = (params) =>{
    // 更新当前用户信息，如果密码修改需要重新登录
    return postRequest('api/user/saveUserInfo', params).then(data =>{
        return data;
    });
}

/**
 * 查询商品列表
 * @param {*} params 
 */
export const productInfoListRequest = (params) => {
    return postRequest('/api/productInfo/productInfoList', params).then(data => {
        return data;
    })
}

/**
 * 查询类别列表
 * @param {*} params 
 */
export const categoryInfoListRequest = (params) => {
    return postRequest('/api/categoryInfo/categoryInfoList', params).then(data => {
        return data;
    })
}

/**
 * 新增商品类别信息
 * @param {*} params 
 */
export const addCategoryInfoRequest = (params) => {
    return postRequest('/api/categoryInfo/addCategoryInfo', params).then(data => {
        return data;
    })
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