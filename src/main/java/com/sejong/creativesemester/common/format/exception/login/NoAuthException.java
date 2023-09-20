package com.sejong.creativesemester.common.format.exception.login;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.login.LoginErrorCode.NO_AUTH_USER;

public class NoAuthException extends ApplicationRunException {

    private static final ErrorEnumCode CODE = NO_AUTH_USER;

    public NoAuthException(ErrorEnumCode code) {
        super(code);
    }

    public NoAuthException(){
        this(CODE);
    }
}
