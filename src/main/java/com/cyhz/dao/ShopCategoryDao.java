package com.cyhz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cyhz.entity.ShopCategory;

public interface ShopCategoryDao {

	/**
	 * 按照条件查询所有的店铺类别
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
	
}
