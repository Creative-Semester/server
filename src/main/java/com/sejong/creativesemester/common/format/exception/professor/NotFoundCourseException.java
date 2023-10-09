package com.sejong.creativesemester.common.format.exception.professor;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.professor.errorCode.ProfessorErrorCode;

public class NotFoundCourseException extends ApplicationRunException {

    private static ErrorEnumCode CODE = ProfessorErrorCode.NOT_FOUND_COURSE;

    private NotFoundCourseException(ErrorEnumCode code){
        super(code);
    }

    public NotFoundCourseException(){
        this(CODE);
    }
}

