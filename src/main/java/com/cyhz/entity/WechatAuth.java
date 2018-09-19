package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

//微信用户
@Getter
@Setter
public class WechatAuth {
	//微信ID
	private Long wechatAuthId;
	//用户ID
	private String openId;
	//创建时间
	private Date createTime;
	//用户实体类
	private PersonInfo personInfo;
}
