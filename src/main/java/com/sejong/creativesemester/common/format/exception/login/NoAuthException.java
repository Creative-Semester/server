package com.sejong.creativesemester.common.format.exception.login;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.login.errorCode.LoginErrorCode;

public class NoAuthException extends ApplicationRunException {

    private static final ErrorEnumCode CODE = LoginErrorCode.NO_AUTH_USER;

    public NoAuthException(ErrorEnumCode code) {
        super(code);
    }

    public NoAuthException(){
        this(CODE);
    }
}
