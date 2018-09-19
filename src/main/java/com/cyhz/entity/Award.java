package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Award {
	private Long awardId;
	private String awardName;
	private String awardDesc;
	private String awardImg;
	private Integer point;
	private Integer priority;
	private Date createTime;
	private Date expireTime;
	private Date lastEditTime;
	private Integer enableStatus;
	private Long shopId;
}
