/**
 * 文件上传请求
 * @param {*} filename 
 * @param {*} file 
 * @param {*} url 
 */
import {fileUploadRequest} from '../api/user'

export const upload = (filename, file, url) => {

    const params = new FormData();
    params.append(filename, file);
    return fileUploadRequest(params, url);
}