package com.sejong.creativesemester.common.format.exception.user;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

import static com.sejong.creativesemester.common.format.exception.user.errorCode.UserErrorCode.NOT_HAVE_ROLE;

public class NotHaveRoleException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = NOT_HAVE_ROLE;

    private NotHaveRoleException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotHaveRoleException(){
        this(CODE);
    }
}
