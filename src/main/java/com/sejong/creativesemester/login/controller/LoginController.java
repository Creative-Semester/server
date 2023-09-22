package com.sejong.creativesemester.login.controller;

import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.login.NoAuthException;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.dto.LoginRequestDto;
import com.sejong.creativesemester.login.dto.SejongMemberResponseDto;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.service.LoginSecurityService;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginSecurityService loginSecurityService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;


    @ApiOperation(value = "로그인 기능",
    notes = "세종대학교 api를 이용하여 로그인 합니다.")
    @PostMapping("/login")
    public SuccessResponse login(@ModelAttribute LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("ACCESS-LOGIN", "o");
        loginSecurityService.doLogin(loginRequestDto);
        log.info("{} : 로그인 성공하였습니다.", loginRequestDto.getId());

        User user = userRepository.findByStudentNum(loginRequestDto.getId()).orElseThrow(NotFoundBoardException::new);
        AuthUser authUser = new AuthUser(user);

        return new SuccessResponse(jwtTokenProvider.createAccessToken(authUser));
    }
}
