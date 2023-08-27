package com.sejong.creativesemester.common.format.exception;

import lombok.Getter;

@Getter
public class ApplicationRunException extends RuntimeException{
    private ErrorEnum errorEnum;
    public ApplicationRunException(ErrorEnum errorEnum){
        super(errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }
}
