package com.sejong.creativesemester.login.dto;


import com.sun.istack.NotNull;
import lombok.*;

@Getter
public class LoginRequestDto {

    @NotNull
    private String id;

    @NotNull
    private String pw;

    @Builder
    public LoginRequestDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

}
