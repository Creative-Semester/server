package com.sejong.creativesemester.common.format.exception.login;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginErrorCode implements ErrorEnumCode {
    NO_AUTH_USER("A001", "로그인 할 수 있는 권한이 없습니다.");

    private final String code;
    private final String message;

}
