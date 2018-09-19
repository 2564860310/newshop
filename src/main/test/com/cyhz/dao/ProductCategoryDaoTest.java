package com.cyhz.dao;

import com.cyhz.BaseTest;
import com.cyhz.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void ProductCategoryDaoTest(){
        List<ProductCategory> productCatrgoryList = productCategoryDao.queryProductCatrgoryList(20);
        productCatrgoryList.forEach(System.out::println);
    }

    @Test
    @Transactional
    public void batchInsertProductCategoryTest(){
        ProductCategory pc1=new ProductCategory();
        pc1.setCreateTime(new Date());
        pc1.setPriority(100);
        pc1.setProductCategoryDesc("123");
        pc1.setProductCategoryName("test");
        pc1.setShopId(30L);
        ProductCategory pc2=new ProductCategory();
        pc2.setCreateTime(new Date());
        pc2.setPriority(90);
        pc2.setProductCategoryDesc("456");
        pc2.setProductCategoryName("test123");
        pc2.setShopId(30L);
        List<ProductCategory> list = new ArrayList<>();
        list.add(pc1);
        list.add(pc2);
        Integer batchNum = productCategoryDao.batchInsertProductCategory(list);
        System.out.println(batchNum);
    }
}
