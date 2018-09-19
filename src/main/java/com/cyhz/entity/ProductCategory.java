package com.cyhz.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategory {
	private Long productCategoryId;
	private Long shopId;
	private String productCategoryName;
	private String productCategoryDesc;
	private Integer priority;
	private Date createTime;
	private Date lastEditTime;

	@Override
	public String toString() {
		return "ProductCategory{" +
				"productCategoryId=" + productCategoryId +
				", shopId=" + shopId +
				", productCategoryName='" + productCategoryName + '\'' +
				", productCategoryDesc='" + productCategoryDesc + '\'' +
				", priority=" + priority +
				", createTime=" + createTime +
				", lastEditTime=" + lastEditTime +
				'}';
	}
}
