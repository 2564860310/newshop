package com.cyhz.dao;

import com.cyhz.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void AreaDaoTest(){
        areaDao.queryAreaList().forEach(System.out::println);
    }

}
