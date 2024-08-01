package com.atmosware.customerManagementSystem.crossCuttingConcerns.exceptions.types;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}