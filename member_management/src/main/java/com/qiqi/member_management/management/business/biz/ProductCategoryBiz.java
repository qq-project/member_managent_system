package com.qiqi.member_management.management.business.biz;

import com.qiqi.member_management.common.constant.Contant;
import com.qiqi.member_management.common.exception.BizException;
import com.qiqi.member_management.common.exception.MsgManagement;
import com.qiqi.member_management.management.business.mapper.ProductCategoryMapper;
import com.qiqi.member_management.management.business.model.CategoryInfo;
import com.qiqi.member_management.management.business.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ProductCategoryBiz类简述
 *
 * @Description
 * @Author shen.chu1
 * @Date 2019年04月22号 9:50
 * @Version 1.0.0
 */
@Service
public class ProductCategoryBiz {

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoryBiz.class);
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public void saveProductCategory(List<ProductCategory> productCategoryList){
        if (null == productCategoryList || productCategoryList.size() == 0){
            // 请求参数为空
            logger.error(MsgManagement.getMsg(100006));
            throw new BizException(100006);
        }
        for (ProductCategory productCategory : productCategoryList){
            productCategoryMapper.saveProductCategory(productCategory);
        }
    }


    /**
     * batchDelStorageInfo(作废商品类别关联关系)
     *
     * @Param 
     * @param productId
     * @return void
     * @exception 
     * @Date  2019-04-22 12:41:32
     **/
    public void batchDelStorageInfo(String productId) {
        // 查询该 productId 所有的数据作废
        List<ProductCategory> lists = queryProductCategoryLists(productId);
        if (null == lists || lists.size() == 0){
            // 为空时不需要作废
            return;
        }
        // 根据查询查询到的列表进行作废
        for(ProductCategory productCategoryDel : lists){
            productCategoryDel.setValid(Contant.VALID_F);
            // 作废
            productCategoryMapper.delStorageInfo(productCategoryDel);
        }
    }

    /**
     * queryProductCategoryLists(根据条件查询商品类别关系列表)
     *
     * @Param 
     * @param productId
     * @return java.util.List<com.qiqi.member_management.management.business.model.ProductCategory>
     * @exception 
     * @Date  2019-04-22 15:24:22
     **/
    public List<ProductCategory> queryProductCategoryLists(String  productId){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductId(productId);
      return productCategoryMapper.queryProductCategoryLists(productCategory);
    }
}
