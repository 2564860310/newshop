package com.cyhz.dao;

import com.cyhz.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

	/**
	 *	分页查询店铺,可输入条件:
	 *  店铺名(模糊),店铺状态,
	 *  店铺类别,区域id,owner
	 * @param shopCondition 查询条件
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition);
	/**
	 * 查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryShopId(@Param("shopId")Long shopId);
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	Integer insertShop(Shop shop);
	/**
	 * 更新店铺
	 * @param shop
	 * @return
	 */
	Integer updateShop(Shop shop);
}
