package com.sejong.creativesemester.login.controller;


import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.login.dto.LoginRequest;
import com.sejong.creativesemester.login.jwt.TokenInfo;
import com.sejong.creativesemester.login.service.LoginSecurityService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginSecurityService loginSecurityService;

    @ApiOperation(value = "로그인 기능",
    notes = "세종대학교 api를 이용하여 로그인 합니다.")
    @PostMapping(value = "/login")
    public SuccessResponse<TokenInfo> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        TokenInfo tokenInfo = loginSecurityService.doLogin(loginRequest.toSejongMember(), httpServletRequest);
        log.info("{} : 로그인 성공하였습니다.", loginRequest.toSejongMember().getId());


        return new SuccessResponse(tokenInfo);
    }

    @ApiOperation(value = "재발급 기능",
    notes = "만료된 토큰을 재발급합니다.")
    @PostMapping(value = "/reissue")
    public SuccessResponse<TokenInfo> reissue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return new SuccessResponse(loginSecurityService.reissueToken(httpServletRequest));
    }

    @ApiOperation(value = "로그아웃 기능",
    notes = "토큰을 만료시켜 로그아웃을 합니다.")
    @PostMapping(value = "/logout")
    public SuccessResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        log.info("{}", httpServletRequest.getParameter("accessToken"));
        loginSecurityService.doLogout(httpServletRequest);

        return SuccessResponse.ok("로그아웃되었습니다.");
    }

}
