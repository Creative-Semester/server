package com.sejong.creativesemester.login.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
@AllArgsConstructor
@Builder
public class TokenRequest {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;
}
