package com.sejong.creativesemester.common.config.format.exception.param;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.config.format.exception.param.errorEnum.ParamErrorCode;

public class NotMatchConditionException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = ParamErrorCode.NOT_MATCH_CONDITION;
    private NotMatchConditionException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotMatchConditionException(){
        this(CODE);
    }
}
