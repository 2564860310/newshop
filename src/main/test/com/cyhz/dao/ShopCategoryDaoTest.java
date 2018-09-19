package com.cyhz.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyhz.BaseTest;
import com.cyhz.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{

	@Autowired
	private ShopCategoryDao shopCategoryDao;
	
	@Test
	public void testQueryShopCategory() {
		ShopCategory shopCategory=new ShopCategory();
		shopCategory.setParentId(10L);
		List<ShopCategory> shopCategoryList=shopCategoryDao.queryShopCategory(shopCategory);
		System.out.println(shopCategoryList.size());
	}
	
}
