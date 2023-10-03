package com.sejong.creativesemester.login;

import com.sejong.creativesemester.login.dto.sejong.SejongMemberRequestDto;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.service.LoginSecurityService;
import org.mockito.Mock;

public class LoginTest {

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private LoginSecurityService loginSecurityService;

    private static SejongMemberRequestDto mockSejongMemberRequestDto() {
        return SejongMemberRequestDto.builder()
                .id("19011729")
                .pw("clark1245!")
                .build();
    }

}
