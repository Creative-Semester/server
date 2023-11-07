package com.sejong.creativesemester.common.format.exception.council;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.NO_MATCH_COUNCILCODE;

public class NoMatchCouncilCodeException extends ApplicationRunException {

    private final static ErrorEnumCode CODE = NO_MATCH_COUNCILCODE;
    private NoMatchCouncilCodeException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NoMatchCouncilCodeException(){
        this(CODE);
    }
}
