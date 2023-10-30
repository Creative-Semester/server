package com.sejong.creativesemester.common.format.exception.professor.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfessorErrorCode implements ErrorEnumCode {

    NOT_MATCH_PROFESSOR("PR001", "해당 교수와 일치하지 않습니다."),
    NOT_FOUND_COURSE("PR002", "해당되는 강의가 존재하지 않습니다.");

    private final String code;
    private final String message;
}
