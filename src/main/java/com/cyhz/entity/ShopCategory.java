package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//店铺类别
@Getter
@Setter
public class ShopCategory {
	//类别ID
	private Long shopCategoryId;
	//类别名称
	private String shopCategoryName;
	//类别描述
	private String shopCategoryDesc;
	//类别图片链接地址
	private String shopCategoryImg;
	//权重(优先级)
	private Integer priority;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEditTime;
	//上一级ID
	private Long parentId;
}
