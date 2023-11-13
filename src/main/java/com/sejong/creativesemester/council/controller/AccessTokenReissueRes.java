package com.sejong.creativesemester.council.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "학생회 권한을 가진 액세스 토큰 재발급")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessTokenReissueRes {
    private String accessToken;
}
