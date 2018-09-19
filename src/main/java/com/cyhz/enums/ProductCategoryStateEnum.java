package com.cyhz.enums;

import lombok.Getter;

@Getter
public enum ProductCategoryStateEnum {

    SUCCESS(1,"创建成功"),INNER_ERROR(-1001,"操作失败"),
    EMPTY_LIST(-1002,"添加数少于1");

    private int state;

    private String stateInfo;

    ProductCategoryStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     *
     * 	 依据传入的index的返回相应的enum值
     *
     * @param index
     * @return
     */
    public static ProductCategoryStateEnum stateof(int index){
        for (ProductCategoryStateEnum state : values()) {
            if (state.getState()==index) {
                return state;
            }
        }
        return null;
    }

}
