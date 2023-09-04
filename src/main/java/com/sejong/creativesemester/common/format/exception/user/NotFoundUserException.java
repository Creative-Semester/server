package com.sejong.creativesemester.common.format.exception.user;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.user.errorCode.UserErrorCode;

public class NotFoundUserException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = UserErrorCode.NOT_FOUND_USER;

    private NotFoundUserException(ErrorEnumCode code){
        super(code);
    }

    public NotFoundUserException(){
        this(CODE);
    }
}
