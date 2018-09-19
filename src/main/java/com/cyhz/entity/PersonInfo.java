package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//用户表
@Getter
@Setter
public class PersonInfo {
	// 用户ID
	private Long userId;
	// 用户姓名
	private String name;
	// 用户头像地址
	private String profileImg;
	// 用户邮箱
	private String email;
	// 用户性别
	private String gender;
	//1.顾客 2.店家 3.管理员
	private Integer enableStatus;
	//用户身份标识
	private Integer userType;
	//创建时间
	private Date createTime;
	//最近更新时间
	private Date lastEditTime;

}
