package com.sejong.creativesemester.login.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccessToken {
    private String accessToken;
    private Long expiration;
}
