package com.sejong.creativesemester.common.format.exception.login;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.login.errorCode.LoginErrorCode.NO_REFRESH_TOKEN;
import static com.sejong.creativesemester.common.format.exception.login.errorCode.LoginErrorCode.NO_VALID_TOKEN;

public class NoValidTokenException extends ApplicationRunException {

    private static final ErrorEnumCode CODE= NO_VALID_TOKEN;

    public NoValidTokenException(ErrorEnumCode code) {
        super(code);
    }

    public NoValidTokenException(){
        this(CODE);
    }
}
