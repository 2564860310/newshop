package com.cyhz.exception;

public class ProductCategoryOperationException extends RuntimeException {

    private static final long serialVersionUID = -6845807110530978527L;

    public ProductCategoryOperationException(String msg){
        super(msg);
    }
}
