package com.cyhz.dao;

import com.cyhz.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDao {

    /**
     * 通过shopId查询商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCatrgoryList(long shopId);

    /**
     * 批量新增商品类别
     * @param productCategoryList
     * @return
     */
    Integer batchInsertProductCategory(List<ProductCategory> productCategoryList);

}
