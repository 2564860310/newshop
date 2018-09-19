package com.cyhz.entity;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

//店铺表
@Getter
@Setter
public class Shop {
	//店铺ID
	private Long shopId;
	//用户ID
	private Long ownerId;
	//类别ID
	private Long shopCategoryId;
	//店铺名称
	private String shopName;
	//店铺描述
	private String shopDesc;
	//店铺具体地址
	private String shopAddr;
	//电话
	private String phone;
	//缩列图地址
	private String shopImg;
	
	private Double longitude;
	
	private Double latitude;
	//权重
	private Integer priority;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEditTime;
	//-1.不可用 0.审核中 1.可用(店铺的状态)
	private Integer enableStatus;
	//管理员给店家的提示消息
	private String advice;
	
	private List<ShopAuthMap> staffList;
	//区域
	private Area area;
	//店铺类别
	private ShopCategory shopCategory;
	//商品父类
	private ShopCategory parentCategory;

	@Override
	public String toString() {
		return "Shop{" +
				"shopId=" + shopId +
				", ownerId=" + ownerId +
				", shopCategoryId=" + shopCategoryId +
				", shopName='" + shopName + '\'' +
				", shopDesc='" + shopDesc + '\'' +
				", shopAddr='" + shopAddr + '\'' +
				", phone='" + phone + '\'' +
				", shopImg='" + shopImg + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", priority=" + priority +
				", createTime=" + createTime +
				", lastEditTime=" + lastEditTime +
				", enableStatus=" + enableStatus +
				", advice='" + advice + '\'' +
				", staffList=" + staffList +
				", area=" + area +
				", shopCategory=" + shopCategory +
				", parentCategory=" + parentCategory +
				'}';
	}
}
