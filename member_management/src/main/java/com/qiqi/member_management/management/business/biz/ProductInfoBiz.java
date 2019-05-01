package com.qiqi.member_management.management.business.biz;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.common.util.CodeBuilderUtil;
import com.qiqi.member_management.management.business.dto.ResponseDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoListRequestDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoSaveRequestDto;
import com.qiqi.member_management.management.business.dto.request.ProductInfoUpdateRequestDto;
import com.qiqi.member_management.management.business.mapper.ProductInfoMapper;
import com.qiqi.member_management.management.business.model.CategoryInfo;
import com.qiqi.member_management.management.business.model.ProductCategory;
import com.qiqi.member_management.management.business.model.ProductInfo;
import com.qiqi.member_management.management.business.model.StorageInfo;
import com.qiqi.member_management.management.business.model.model.extend.ProductInfoExtend;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductInfoBiz类简述
 *
 * @Description
 * @Author qiqi
 * @Date 2019年04月17号 19:33
 * @Version 1.0.0
 */
@Service
public class ProductInfoBiz {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private StorageInfoBiz storageInfoBiz;

    @Autowired
    private ProductCategoryBiz productCategoryBiz;

    @Autowired
    private CategoryInfoBiz categoryInfoBiz;
    
    private static final Logger logger = LoggerFactory.getLogger(ProductInfoBiz.class);
    
    /**
     * productInfoList(商品列表查询)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-17 19:41:11
     **/
    public ResponseDto productInfoList(ProductInfoListRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        List<ProductInfoExtend> productInfoList = productInfoMapper.productInfoList(requestDto);
        for (ProductInfoExtend productInfoExtend : productInfoList){
            // 根据 productId 查询 查询商品类别关系
            productInfoExtend.setCategoryInfoList(categoryInfoBiz.queryCategoryListByProductId(productInfoExtend.getProductId()));
        }
        responseDto.setResCode(ResponseDto.SUCCESS);
        responseDto.setResult(productInfoList);
        return responseDto;
    }

    /**
     * saveProductInfo(新增商品信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-19 19:30:30
     **/
    @Transactional
    public ResponseDto saveProductInfo(ProductInfoSaveRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        // 根据商品名称，查询是否存在同名商品
        ProductInfo productInfo = productInfoMapper.queryProductInfoByName(requestDto.getProductName());
        if (null != productInfo){
          logger.error(MsgManagement.getMsg(100031, requestDto.getProductName()));
          throw new BizException(100031, requestDto.getProductName());
        }
        /**
         *  1、此处应该新增一条商品记录
         *  2、此处应该新增多条商品类别关系记录
         **/
        productInfo = new ProductInfo();
        List<ProductCategory> productCategories = new ArrayList<>();
        StorageInfo storageInfo = new StorageInfo();
        // 处理参数
        handleSaveProductInfoAttr(productInfo, productCategories, storageInfo, requestDto);
        // 新增商品信息
        productInfoMapper.saveProductInfo(productInfo);
        // 新增商品类别管理信息
        productCategoryBiz.saveProductCategory(productCategories);
        // 新增库存信息
        storageInfoBiz.saveStorageInfo(storageInfo);
        return responseDto;
    }

    /**
     * handleSaveProductInfoAttr(处理新增商品参数)
     *
     * @Param 
     * @param productInfo
     * @param productCategories
     * @param storageInfo
     * @param requestDto
     * @return void
     * @exception 
     * @Date  2019-04-22 08:58:57
     **/
    private void handleSaveProductInfoAttr(ProductInfo productInfo,
                                           List<ProductCategory> productCategories,
                                           StorageInfo storageInfo, 
                                           ProductInfoSaveRequestDto requestDto) {
        // 商品编号 
        String productId = CodeBuilderUtil.codeGenerator(productInfoMapper.getLastProductInfo().getId(), Contant.PRODUCT_ID_LENGTH, Contant.PRODUCT_ID_PREFIX);
        // 处理商品信息
        productInfo.setProductId(productId);
        productInfo.setProductName(requestDto.getProductName());
        productInfo.setProductImgSrc(requestDto.getProductSrc());
        productInfo.setDescribute(requestDto.getDescribute());
        productInfo.setPrice(requestDto.getPrice());
        // 库存信息处理，生成库存编号
        // 常量处理
        productInfo.setAddedByName(Contant.ADDED_NAME);
        productInfo.setLastModifiedByName(Contant.ADDED_NAME);
        productInfo.setValid(Contant.VALID_T);
        // 处理库存信息
        storageInfo.setStorageId(CodeBuilderUtil.codeGenerator(storageInfoBiz.getLastStorageInfo().getId(), Contant.STORAGE_ID_LENGTH, Contant.STORAGE_ID_PREFIX));
        // 库存名默认就是商品名
        storageInfo.setStorageName(requestDto.getProductName());
        storageInfo.setProductId(productId);
        storageInfo.setProductAmount(requestDto.getProductAmount());
        storageInfo.setAddedName(Contant.ADDED_NAME);
        storageInfo.setLastModifiedByName(Contant.ADDED_NAME);
        storageInfo.setProductAmount(requestDto.getProductAmount());
        storageInfo.setValid(Contant.VALID_T);
        // 处理 商品类别关系信息
        for(CategoryInfo categoryInfo : requestDto.getCategoryIdList()){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductId(productId);
            productCategory.setCategoryId(categoryInfo.getCategoryId());
            // 常量处理
            productCategory.setAddedByName(Contant.ADDED_NAME);
            productCategory.setLastModifiedByName(Contant.ADDED_NAME);
            productCategory.setValid(Contant.VALID_T);
            productCategories.add(productCategory);
        }
    }

    /**
     * deleteProductInfo(删除商品信息)
     *
     * @Param 
     * @param productInfo
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-22 11:43:21
     **/
    @Transactional
    public ResponseDto deleteProductInfo(ProductInfo productInfo) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        if(null == productInfo.getId()
                || StringUtils.isBlank(productInfo.getProductId())){
            logger.error(MsgManagement.getMsg(100006));
            throw new BizException(100006);
        }
        // 作废商品信息
        productInfoMapper.deleteProductInfo(productInfo);
        // 作废商品库存信息
        StorageInfo storageInfo = new StorageInfo();
        storageInfo.setValid(Contant.VALID_F);
        storageInfoBiz.updateStorageInfo(storageInfo);
        // 批量作废商品类别关联信息
        productCategoryBiz.batchDelStorageInfo(productInfo.getProductId());
        return responseDto;
    }

    /**
     * updateProductInfo(修改商品信息)
     *
     * @Param 
     * @param requestDto
     * @return com.qiqi.member_management.management.business.dto.ResponseDto
     * @exception 
     * @Date  2019-04-22 15:01:00
     **/
    public ResponseDto updateProductInfo(ProductInfoUpdateRequestDto requestDto) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResCode(ResponseDto.SUCCESS);
        //查询商品信息
        ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(requestDto.getId());
        // 判断名称是否存在修改
        if(!productInfo.getProductName().equals(requestDto.getProductName())){
            // 名称存在修改，查询是否存在同名商品
            ProductInfo pro = productInfoMapper.queryProductInfoByName(requestDto.getProductName());
            if (null != pro){
                logger.error(MsgManagement.getMsg(100031, requestDto.getProductName()));
                throw new BizException(100031, requestDto.getProductName());
            } else {
                productInfo.setProductName(requestDto.getProductName());
            }
        }
        productInfo.setPrice(requestDto.getPrice());
        productInfo.setDescribute(requestDto.getDescribute());
        productInfo.setProductImgSrc(requestDto.getProductImgSrc());
        // 判断是否超过指定库存
        StorageInfo storageInfo = storageInfoBiz.queryStorageByProductId(requestDto.getProductId());
        if (null == storageInfo){
            throw new BizException(100033, storageInfo.getStorageSize().toString());
        }
        int compareVal = -1;
        if(storageInfo.getStorageSize().compareTo(requestDto.getProductAmount()) == compareVal ){
            logger.error(MsgManagement.getMsg(100034));
            throw new BizException(100034);
        }
        // 更新商品数量
        storageInfo.setProductAmount(requestDto.getProductAmount());
        // 更新商品信息
        productInfoMapper.updateProductInfo(productInfo);
        // 更新库存信息
        storageInfoBiz.updateStorageInfo(storageInfo);
        // 作废原有的商品类别关系
        productCategoryBiz.batchDelStorageInfo(requestDto.getProductId());
        // 新增修改后的
        List<ProductCategory> productCategories  = new ArrayList<>();
        // 处理 商品类别关系信息
        for(CategoryInfo categoryInfo : requestDto.getCategoryInfoList()){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductId(requestDto.getProductId());
            productCategory.setCategoryId(categoryInfo.getCategoryId());
            // 常量处理
            productCategory.setAddedByName(Contant.ADDED_NAME);
            productCategory.setLastModifiedByName(Contant.ADDED_NAME);
            productCategory.setValid(Contant.VALID_T);
            productCategories.add(productCategory);
        }
        productCategoryBiz.saveProductCategory(productCategories);
        return responseDto;
    }

    /**
     * queryProductInfo(根据商品编号获取商品信息)
     *
     * @Param 
     * @param productId
     * @return com.qiqi.member_management.management.business.model.ProductInfo
     * @exception 
     * @Date  2019-05-01 10:20:38
     **/
    public ProductInfo queryProductInfo(String productId) {
        return productInfoMapper.queryProductInfo(productId);
    }
}
