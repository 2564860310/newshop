package com.cyhz.service;

import com.cyhz.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaServiceImpl;

    @Test
    public void AreaServiceTest(){
        areaServiceImpl.getAreaList().forEach(System.out::println);
    }
}
