package com.cyhz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result<T> {
    /**
     * 封装json对象,所有返回结果都使用它
     */
    private boolean success;
    private T data;
    private String errorMsg;
    private int errorCode;

    public Result(){
        super();
    }

    /**
     * 成功的时候的构造
     * @param success
     * @param data
     */
    public Result(boolean success,T data){
        this.success=success;
        this.data=data;
    }

    /**
     * 失败的时候的构造器
     * @param success
     * @param errorCode
     * @param errorMsg
     */
    public Result(boolean success,int errorCode,String errorMsg){
        this.success=success;
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }

}
