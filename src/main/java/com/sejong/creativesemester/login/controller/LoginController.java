package com.sejong.creativesemester.login.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sejong.creativesemester.common.domain.Helper;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.domain.RefreshToken;
import com.sejong.creativesemester.login.dto.LoginRequest;
import com.sejong.creativesemester.login.dto.refreshToken.RefreshTokenDto;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.jwt.TokenInfo;
import com.sejong.creativesemester.login.repository.RefreshTokenRepository;
import com.sejong.creativesemester.login.service.LoginSecurityService;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginSecurityService loginSecurityService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @ApiOperation(value = "로그인 기능",
    notes = "세종대학교 api를 이용하여 로그인 합니다.")
    @PostMapping(value = "/login")
    public SuccessResponse login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("authorization", "*");

        loginSecurityService.doLogin(loginRequest.toSejongMember());
        log.info("{} : 로그인 성공하였습니다.", loginRequest.toSejongMember().getId());


        User user = userRepository.findByStudentNum(loginRequest.getId()).orElseThrow(NotFoundUserException::new);
        AuthUser authUser = new AuthUser(user);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authUser);

        refreshTokenRepository.save(RefreshToken.builder()
                        .studentNum(authUser.getStudentNum())
                        .userName(authUser.getUsername())
                        .role(authUser.getRole())
                        .ip(Helper.getClientIp(httpServletRequest))
                        .refreshToken(tokenInfo.getRefreshToken())
                .build());

        return new SuccessResponse(tokenInfo);
    }

    @ApiOperation(value = "재발급 기능",
    notes = "만료된 토큰을 재발급합니다.")
    @PostMapping(value = "/reissue")
    public SuccessResponse reissue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("{}", httpServletRequest.getParameter("accessToken"));

        TokenInfo tokenInfo = loginSecurityService.reissueToken(httpServletRequest);

        return new SuccessResponse(tokenInfo);
    }
    

}
