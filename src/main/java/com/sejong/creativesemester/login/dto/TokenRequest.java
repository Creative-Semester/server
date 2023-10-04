package com.sejong.creativesemester.login.dto;

import com.sun.istack.NotNull;
import lombok.*;


@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {

    @NotNull
    private String accessToken;

    @NotNull
    private String refreshToken;

    public TokenRequest tokenRequest(){
        return TokenRequest.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
