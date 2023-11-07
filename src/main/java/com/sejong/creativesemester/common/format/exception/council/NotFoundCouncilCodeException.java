package com.sejong.creativesemester.common.format.exception.council;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.NOT_FOUND_COUNCILCODE;

public class NotFoundCouncilCodeException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = NOT_FOUND_COUNCILCODE;
    private NotFoundCouncilCodeException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotFoundCouncilCodeException(){
        this(CODE);
    }
}
