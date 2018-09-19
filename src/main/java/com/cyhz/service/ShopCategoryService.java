package com.cyhz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyhz.entity.ShopCategory;

public interface ShopCategoryService {
	
	List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition);

}
