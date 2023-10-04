package com.sejong.creativesemester.common.format.exception.user;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.user.errorCode.UserErrorCode;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;

public class NotFoundUserException extends ApplicationRunException {
    private static final ErrorEnumCode CODE = UserErrorCode.NOT_FOUND_USER;

    private NotFoundUserException(ErrorEnumCode code){
        super(code);
    }

    public NotFoundUserException(){
        this(CODE);
    }
}
