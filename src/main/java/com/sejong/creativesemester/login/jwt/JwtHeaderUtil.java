package com.sejong.creativesemester.login.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtHeaderUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;


}
