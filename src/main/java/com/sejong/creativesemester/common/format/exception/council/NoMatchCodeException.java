package com.sejong.creativesemester.common.format.exception.council;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.NO_MATCH_CODE;

public class NoMatchCodeException extends ApplicationRunException {

    private final static ErrorEnumCode CODE = NO_MATCH_CODE;
    private NoMatchCodeException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NoMatchCodeException(){
        this(CODE);
    }
}
