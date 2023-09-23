package com.sejong.creativesemester.login.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    @NotNull
    private String id;
    @NotNull
    private String pw;

    public SejongMemberRequestDto toSejongMember(){
        return SejongMemberRequestDto.builder()
                .id(id)
                .pw(pw)
                .build();
    }
}
