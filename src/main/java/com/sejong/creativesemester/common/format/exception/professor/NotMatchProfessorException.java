package com.sejong.creativesemester.common.format.exception.professor;

import com.sejong.creativesemester.common.format.exception.ApplicationRunException;
import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import com.sejong.creativesemester.common.format.exception.professor.errorCode.ProfessorErrorCode;

public class NotMatchProfessorException extends ApplicationRunException {

    private static ErrorEnumCode CODE = ProfessorErrorCode.NOT_MATCH_PROFESSOR;

    private NotMatchProfessorException(ErrorEnumCode code){
        super(code);
    }

    public NotMatchProfessorException(){
        this(CODE);
    }
}
