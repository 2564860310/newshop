package com.cyhz.dao;

import java.util.List;

import com.cyhz.entity.Area;

public interface AreaDao {
	/**
	 * 查询所有区域列表
	 * @return
	 */
	List<Area> queryAreaList();

}
