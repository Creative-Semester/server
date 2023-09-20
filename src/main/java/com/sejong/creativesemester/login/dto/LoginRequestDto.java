package com.sejong.creativesemester.login.dto;


import lombok.*;

@Getter
@Setter
public class LoginRequestDto {

    private String id;
    private String pw;

    @Builder
    public LoginRequestDto(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

}
