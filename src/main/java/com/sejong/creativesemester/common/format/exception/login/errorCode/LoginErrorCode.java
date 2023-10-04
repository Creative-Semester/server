package com.sejong.creativesemester.common.format.exception.login.errorCode;

import com.sejong.creativesemester.common.format.exception.ErrorEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginErrorCode implements ErrorEnumCode {
    NO_AUTH_USER("L001", "로그인 할 수 있는 권한이 없습니다."),
    NO_REFRESH_TOKEN("L002","리프레시토큰을 가지고 있지 않습니다."),
    NO_VALID_TOKEN("L003", "토큰 갱신에 실패하였습니다.");

    private final String code;
    private final String message;

}
