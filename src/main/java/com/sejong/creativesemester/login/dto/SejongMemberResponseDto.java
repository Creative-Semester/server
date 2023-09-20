package com.sejong.creativesemester.login.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class SejongMemberResponseDto {
    private String msg;
    private SejongMemberResponseResult result;


}
