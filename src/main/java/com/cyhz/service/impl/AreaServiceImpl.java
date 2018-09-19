package com.cyhz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyhz.dao.AreaDao;
import com.cyhz.entity.Area;
import com.cyhz.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	
	@Autowired
	private AreaDao dao;
	@Override
	public List<Area> getAreaList() {
		return dao.queryAreaList();
	}

}
