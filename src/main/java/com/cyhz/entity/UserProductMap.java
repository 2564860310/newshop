package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProductMap {
	private Long userProductId;
	private Long userId;
	private Long productId;
	private Long shopId;
	private String userName;
	private String productName;
	private Date createTime;
	private Integer point;
	private PersonInfo user;
	private Product product;
	private Shop shop;
}
