package com.sejong.creativesemester.user.service;

import com.sejong.creativesemester.user.controller.UserInfoResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserInfoResponseDto {
    private String majorName;
    private String name;
    public UserInfoResponse toResponse(){
        return UserInfoResponse.builder()
                .majorName(majorName)
                .name(name)
                .build();
    }
}
