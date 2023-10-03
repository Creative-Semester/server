package com.sejong.creativesemester.common.config.format.exception.user;

import com.sejong.creativesemester.common.config.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.config.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.config.format.exception.user.errorCode.UserErrorCode.NOT_HAVE_ROLE;

public class NotHaveRoleException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = NOT_HAVE_ROLE;

    private NotHaveRoleException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotHaveRoleException(){
        this(CODE);
    }
}
