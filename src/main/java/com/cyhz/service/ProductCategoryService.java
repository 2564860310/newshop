package com.cyhz.service;

import com.cyhz.dto.ProductCategoryExection;
import com.cyhz.entity.ProductCategory;
import com.cyhz.exception.ProductCategoryOperationException;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 按照shopId查询商品类别
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);

    ProductCategoryExection batchAddProductCategory(List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException;
}
