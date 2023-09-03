package com.sejong.creativesemester.common.format.exception.council;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.council.errorCode.CouncilErrorCode.ONLY_USE_COUNCIL;

public class OnlyUseCouncilException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = ONLY_USE_COUNCIL;
    private OnlyUseCouncilException(ErrorEnumCode CODE){
        super(CODE);
    }
    public OnlyUseCouncilException(){
        this(CODE);
    }
}
