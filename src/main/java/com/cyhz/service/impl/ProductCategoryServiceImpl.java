package com.cyhz.service.impl;

import com.cyhz.dao.ProductCategoryDao;
import com.cyhz.dto.ProductCategoryExection;
import com.cyhz.entity.ProductCategory;
import com.cyhz.enums.ProductCategoryStateEnum;
import com.cyhz.exception.ProductCategoryOperationException;
import com.cyhz.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCatrgoryList(shopId);
    }

    @Override
    @Transactional
    public ProductCategoryExection batchAddProductCategory
            (List<ProductCategory> productCategoryList)
            throws ProductCategoryOperationException {
        if (productCategoryList != null & productCategoryList.size() > 0) {
            try {
                Integer effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败！");
                } else {
                    return new ProductCategoryExection(ProductCategoryStateEnum.SUCCESS);
                }
            }catch (Exception e){
                throw new ProductCategoryOperationException("batchProductCategory error"+e.getMessage());
            }
        } else {
            return new ProductCategoryExection(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }


}
