package com.qiqi.member_management.management.business.biz;

import com.alibaba.fastjson.JSONObject;
import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.mapper.CategoryInfoMapper;
import com.qiqi.member_management.management.business.model.CategoryInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * CategoryInfoBiz类简述
 *  库存信息业务处理类
 * @Description
 * @Author qiqi
 * @Date 2019年04月17号 19:34
 * @Version 1.0.0
 */
@Service
public class CategoryInfoBiz {

    @Autowired
    private CategoryInfoMapper categoryInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(CategoryInfoBiz.class);

    /**
     * categoryInfoList(查询类别列表)
     *
     * @Param 
     * @param params
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-18 09:39:26
     **/
    public ResponseDto categoryInfoList(Map<String,Object> params) {
        List<CategoryInfo> categoryInfos =  categoryInfoMapper.categoryInfoList(params);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(JSONObject.toJSON(categoryInfos));
        return responseDto;
    }

    /**
     * addCategoryInfo(新增类别信息)
     *
     * @Param 
     * @param params
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-18 10:28:26
     **/
    @Transactional
    public ResponseDto addCategoryInfo(Map<String,Object> params) {
        ResponseDto responseDto = new ResponseDto();
        // 根据 categoryName 查询类别信息
        String categoryName = (String) params.get("categoryName");
        if(StringUtils.isBlank(categoryName)){
             logger.error(MsgManagement.getMsg(100020));
             throw new BizException(100020);
        }
        CategoryInfo categoryInfo = handleCategoryInfo(categoryName, params);
        categoryInfoMapper.addCategoryInfo(categoryInfo);
        responseDto.setResCode(ResponseDto.SUCCESS);
        return responseDto;
    }

    /**
     * handleCategoryInfo(处理新增数据)
     *
     * @Param 
     * @param categoryName
     * @param params
     * @return com.qiqi.member_management.management.business.model.CategoryInfo
     * @exception 
     * @Date  2019-04-18 10:47:06
     **/
    private CategoryInfo handleCategoryInfo(String categoryName, Map<String,Object> params) {
        CategoryInfo categoryInfo = categoryInfoMapper.queryCategoryInfoByName(params);
        if (null != categoryInfo){
            logger.error(MsgManagement.getMsg(100021));
            throw new BizException(100021, categoryName);
        }
        categoryInfo = new CategoryInfo();
        categoryInfo.setCategoryName(categoryName);
        categoryInfo.setDescribute(String.valueOf(params.get("describute")));
        // 设置类别编号 C + 6位数字(0... + id)
        categoryInfo.setCategoryId(CodeBuilderUtil.codeGenerator(getLastCategoryInfo().getId(),
                Contant.CATEGORY_ID_LENGTH,
                Contant.CATEGORY_ID_PREFIX));
        // 随机图片 type处理
        categoryInfo.setCategoryType(randomTypes());
        return setCommonsAttr(categoryInfo);
    }

    /**
     * getLastCategoryInfo(获取最新一条数据)
     *
     * @Param 
     * @return com.qiqi.member_management.management.business.model.CategoryInfo
     * @exception 
     * @Date  2019-04-18 10:50:46
     **/
    public CategoryInfo getLastCategoryInfo(){
        CategoryInfo categoryInfo = categoryInfoMapper.getLastCategoryInfo();
        if(null == categoryInfo){
            // 表示当前数据库未存在数据，默认为id为1
            categoryInfo = new CategoryInfo();
            categoryInfo.setId(1);
        }
        return categoryInfo;
    }

    /**
     * setCommonsAttr(设置固定参数)
     *
     * @Param 
     * @param categoryInfo
     * @return com.qiqi.member_management.management.business.model.CategoryInfo
     * @exception 
     * @Date  2019-04-18 10:56:01
     **/
    private CategoryInfo setCommonsAttr(CategoryInfo categoryInfo){
        categoryInfo.setRemark("12345656");
        categoryInfo.setAddedByName(Contant.ADDED_NAME);
        categoryInfo.setLastModifiedByName(Contant.ADDED_NAME);
        categoryInfo.setValid(Contant.VALID_T);
        return categoryInfo;
    }

    /**
     * randomTypes(随机生成标签颜色)
     *
     * @Param 
     * @return java.lang.String
     * @exception 
     * @Date  2019-04-18 11:01:38
     **/
    public String randomTypes(){
        Random random = new Random();
        return Contant.CATEGORY_TYPES[random.nextInt(3)];
    }

    /**
     * queryCategoryListByProductId(根据productId查询类别列表)
     *
     * @Param 
     * @param productId
     * @return java.util.List<com.qiqi.member_management.management.business.model.CategoryInfo>
     * @exception 
     * @Date  2019-04-22 10:39:47
     **/
    public List<CategoryInfo> queryCategoryListByProductId(String productId) {
        List<CategoryInfo> categoryInfos = categoryInfoMapper.queryCategoryListByProductId(productId);
        if (null == categoryInfos || categoryInfos.size() ==0){
            throw new BizException("商品类别为空");
        }
        return categoryInfos;
    }
}
