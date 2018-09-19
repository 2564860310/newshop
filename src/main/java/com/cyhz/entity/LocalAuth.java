package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//本地用户表
@Getter
@Setter
public class LocalAuth{
	//本地ID
	private Long localAuthId;
	//用户名
	private String username;
	//密码
	private String password;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEidtTime;
	//用户实体
	private PersonInfo personInfo;
}
