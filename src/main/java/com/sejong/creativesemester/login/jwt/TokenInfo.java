package com.sejong.creativesemester.login.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Schema(description = "토큰 정보값")
@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

    @Schema(description = "JWT에 대한 인증 타입")
    private String grantType;
    @Schema(description = "accessToken값, api통신할때 해당값을 헤더에 넣어 보내주어야함")
    private String accessToken;
    @Schema(description = "refreshToken값, accessToken만료시 재발급을 위한 토큰, 해당값조차 만료되면 재로그인 진행해야함")
    private String refreshToken;
    @Schema(description = "accessToken 만료시간")
    private Long accessTokenExpiration;
    @Schema(description = "refreshToken 만료시간`")
    private Long refreshTokenExpiration;
}
