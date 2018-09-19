package com.cyhz.dao;

import com.cyhz.BaseTest;
import com.cyhz.entity.Area;
import com.cyhz.entity.Shop;
import com.cyhz.entity.ShopCategory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void queryShopList(){
        Integer pageIndex=2;
        PageHelper.startPage(pageIndex,5);
        Shop shopCondition=new Shop();
        ShopCategory shopCategory=new ShopCategory();
        Area area=new Area();
        //area.setAreaId(3);
        //shopCategory.setShopCategoryId(14L);
        //shopCondition.setOwnerId(8L);
        shopCondition.setEnableStatus(1);
        //shopCondition.setShopName("二");
        shopCondition.setArea(area);
        shopCondition.setShopCategory(shopCategory);
        Page<Shop> shopList =(Page<Shop>) shopDao.queryShopList(shopCondition);
        //System.out.println(shopList.getResult().get(0));
    }

    @Test
    public void ShopByIdDaoTest(){
        Shop shop = shopDao.queryShopId(15L);
        System.out.println(shop);
        System.out.println(shop.getParentCategory().getShopCategoryId());
        System.out.println(shop.getShopCategory().getShopCategoryId());
    }

    @Test
    public void UpdateShopDaoTest(){
        Shop shop=new Shop();
        shop.setShopId(29L);
        shop.setShopName("测试店铺");
        shop.setShopDesc("地址");
        shop.setAdvice("审核中");
        shop.setEnableStatus(1);
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setShopImg("test");
        Integer num = shopDao.updateShop(shop);
        System.out.println(num);
    }

    @Test
    public void InsertShopDaoTest() {
        Shop shop=new Shop();
        Area area=new Area();
        area.setAreaId(3);
        ShopCategory shopCategory=new ShopCategory();
        shopCategory.setShopCategoryId(11L);
        shop.setArea(area);
        shop.setOwnerId(8L);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺");
        shop.setShopDesc("test");
        shop.setAdvice("审核中");
        shop.setEnableStatus(1);
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setCreateTime(new Date());
        shop.setShopImg("test");
        Integer num = shopDao.insertShop(shop);
        System.out.println(num);
    }
}
