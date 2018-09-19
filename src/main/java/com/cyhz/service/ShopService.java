package com.cyhz.service;

import java.io.InputStream;
import java.util.List;
import com.cyhz.dto.ShopExecution;
import com.cyhz.entity.Shop;
import com.cyhz.exception.ShopOperationException;

public interface ShopService {
	/**
	 *	分页查询店铺,可输入条件:
	 *  店铺名(模糊),店铺状态,
	 *  店铺类别,区域id,owner
	 * @param shopCondition 查询条件
	 * @return
	 */
	ShopExecution queryShopList(Shop shopCondition);
	/**
	 * 通过店铺id获取信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(Long shopId);
	/**
	 * 注册店铺信息，以及对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	/**
	 * 更新店铺信息，以及对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

}
