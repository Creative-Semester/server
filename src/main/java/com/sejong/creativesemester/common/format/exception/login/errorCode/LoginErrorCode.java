package com.sejong.creativesemester.common.format.exception.login.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginErrorCode implements ErrorEnumCode {
    NO_AUTH_USER("L001", "로그인 할 수 있는 권한이 없습니다."),
    NO_REFRESH_TOKEN("L002","refreshToken을 가지고 있지 않습니다."),
    NO_VALID_TOKEN("L003", "유효하지 않은 토큰입니다.");

    private final String code;
    private final String message;

}
