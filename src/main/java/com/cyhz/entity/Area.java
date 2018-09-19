package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//区域
@Getter
@Setter
public class Area {

	// ID
	private Integer areaId;
	// 名称
	private String areaName;
	// 权重
	private Integer priority;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date lastEditTime;

}
