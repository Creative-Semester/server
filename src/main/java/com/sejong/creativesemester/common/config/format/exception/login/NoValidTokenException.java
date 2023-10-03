package com.sejong.creativesemester.common.config.format.exception.login;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.config.format.exception.login.errorCode.LoginErrorCode;

public class NoValidTokenException extends ApplicationRunException {

    private static final ErrorEnumCode CODE= LoginErrorCode.NO_VALID_TOKEN;

    public NoValidTokenException(ErrorEnumCode code) {
        super(code);
    }

    public NoValidTokenException(){
        this(CODE);
    }
}
