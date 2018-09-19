package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//头条表
@Getter
@Setter
public class HeadLine {
	//头条id
	private Long lindId;
	//头条名字
	private String lineName;
	//头条链接
	private String lineLink;
	//头条图片
	private String lineImg;
	//头条权重(优先级的问题)
	private Integer priority;
	//0.不可用 1.可用 (属性)
	private Integer enableStatus;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEidtTime;
}
