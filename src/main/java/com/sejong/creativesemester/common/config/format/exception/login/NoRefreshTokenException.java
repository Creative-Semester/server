package com.sejong.creativesemester.common.config.format.exception.login;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.config.format.exception.login.errorCode.LoginErrorCode.NO_REFRESH_TOKEN;

public class NoRefreshTokenException extends ApplicationRunException {

    private static final ErrorEnumCode CODE= NO_REFRESH_TOKEN;

    public NoRefreshTokenException(ErrorEnumCode code) {
        super(code);
    }

    public NoRefreshTokenException(){
        this(CODE);
    }


}
