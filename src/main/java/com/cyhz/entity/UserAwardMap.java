package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAwardMap {

	private Long userAwardId;
	private Long userId;
	private Long awardId;
	private Long shopId;
	private String userName;
	private String awardName;
	private Date expireTime;
	private Date createTime;
	private Integer usedStatus;
	private Integer point;
	private PersonInfo user;
	private Award award;
	private Shop shop;
}
