package com.cyhz.dto;

import com.cyhz.entity.ProductCategory;
import com.cyhz.enums.ProductCategoryStateEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCategoryExection {

    private Integer state;

    private String stateInfo;

    private List<ProductCategory> productCategoryList;

    public ProductCategoryExection() {
        super();
    }

    //失败的时候使用的构造器
    public ProductCategoryExection(ProductCategoryStateEnum stateEnum){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }
    //成功的时候使用的构造器
    public ProductCategoryExection(ProductCategoryStateEnum stateEnum,List<ProductCategory> productCategoryList){
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.productCategoryList=productCategoryList;
    }
}
