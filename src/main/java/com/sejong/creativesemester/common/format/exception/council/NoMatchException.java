package com.sejong.creativesemester.common.format.exception.council;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.NOT_FOUND_CODE;
import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.NO_MATCH;

public class NoMatchException extends ApplicationRunException {

    private final static ErrorEnumCode CODE = NO_MATCH;
    private NoMatchException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NoMatchException(){
        this(CODE);
    }
}
