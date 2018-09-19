package com.cyhz.service.impl;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cyhz.dao.ShopDao;
import com.cyhz.dto.ShopExecution;
import com.cyhz.entity.Shop;
import com.cyhz.enums.ShopStateEnum;
import com.cyhz.exception.ShopOperationException;
import com.cyhz.service.ShopService;
import com.cyhz.util.ImageUtil;
import com.cyhz.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopDao shopDao;

	@Override
	public ShopExecution queryShopList(Shop shopCondition) {
		PageHelper.startPage(0,100);
		Page<Shop> page= (Page<Shop>) shopDao.queryShopList(shopCondition);
		ShopExecution se = new ShopExecution();
		if (page!=null) {
			se.setShopList(page.getResult());
			se.setCount((int)page.getTotal());
		}else {
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}

	@Override
	public Shop getByShopId(Long shopId) {
		return shopDao.queryShopId(shopId);
	}

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName) {
		//空值判断
		if(shop==null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//初始值，给店铺信息赋值
			shop.setEnableStatus(ShopStateEnum.CHECK.getState());
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			//添加店铺信息
			int effectedNum=shopDao.insertShop(shop);
			if(effectedNum<=0) {
				throw new ShopOperationException("店铺创建失败");
			}else {
				if(shopImgInputStream !=null){
					//存储图片
					try {
						addShopImg(shop,shopImgInputStream,fileName);
					}catch(Exception e) {
						throw new ShopOperationException("addShopImg error"+e.getMessage());
					}
					effectedNum=shopDao.updateShop(shop);
					if(effectedNum<=0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		}catch(Exception e) {
			throw new ShopOperationException("addShop error"+e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
		if (shop == null || shop.getShopId() == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		} else {
			try {
				//判断是否需要处理图片
				if (shopImgInputStream != null & fileName != null & !"".equals(fileName)) {
					Shop tempShop = shopDao.queryShopId(shop.getShopId());
					/**
					 * 判断图片是否为空
					 * 如果不等于空就删除该目录下的
					 * 图片同时删除该目录
					 */
					if (tempShop.getShopImg() != null) {
						ImageUtil.deleteFileOrPath(tempShop.getShopImg());
					}
					addShopImg(shop, shopImgInputStream, fileName);
				}
				//更新店铺信息
				shop.setLastEditTime(new Date());
				int effectedNum = shopDao.updateShop(shop);
				if (effectedNum <= 0) {
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				} else {
					shop = shopDao.queryShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS, shop);
				}
			} catch (Exception e) {
				throw new ShopOperationException("medifyShop error" + e.getMessage());
			}
		}
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream,String fileName) {
		//获取shop图片目录的相对值路径
		String dest =PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr=ImageUtil.generateThumbnail(shopImgInputStream,fileName,dest);
		shop.setShopImg(shopImgAddr);
	}
	
}
