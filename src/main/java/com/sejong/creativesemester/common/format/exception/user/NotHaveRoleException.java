package com.sejong.creativesemester.common.format.exception.user;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.user.errorCode.UserErrorCode;

public class NotHaveRoleException extends ApplicationRunException {
    private final static ErrorEnumCode CODE = UserErrorCode.NOT_HAVE_ROLE;

    private NotHaveRoleException(ErrorEnumCode CODE){
        super(CODE);
    }
    public NotHaveRoleException(){
        this(CODE);
    }
}
