package com.cyhz.service;

import com.cyhz.BaseTest;
import com.cyhz.dto.ShopExecution;
import com.cyhz.entity.Shop;
import com.cyhz.exception.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopServiceImpl;

    @Test
    public void medifyShopTest()throws ShopOperationException, FileNotFoundException {
        Shop shop=new Shop();
        shop.setOwnerId(8L);
        shop.setShopId(30L);
        shop.setShopName("你的小宝贝1");
        File file=new File("D:/0.jpg");
        FileInputStream fileInputStream=new FileInputStream(file);
        ShopExecution shopExecution = shopServiceImpl.modifyShop(shop, fileInputStream, "0.jpg");
        System.out.println("新图片路径："+shopExecution.getShop().getShopImg());
    }

}
