package com.cyhz.dto;

import java.util.List;

import com.cyhz.entity.Shop;
import com.cyhz.enums.ShopStateEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopExecution {

	// 结果状态
	private int state;

	// 状态标识
	private String stateInfo;
	// 店铺数量
	private int count;

	// 操作的shop(增删改店铺的时候用到)
	private Shop shop;

	// shop列表(查询店铺列表的时候使用)
	private List<Shop> shopList;

	public ShopExecution() {
		super();
	}

	// 店铺操作失败的时候使用的构造器
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	// 店铺操作成功的时候的构造器
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shop = shop;
	}

	// 店铺操作成功的时候的构造器
	public ShopExecution(ShopStateEnum stateEnum, Shop shop,List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}

}
