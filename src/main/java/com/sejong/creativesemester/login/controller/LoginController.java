package com.sejong.creativesemester.login.controller;

import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.dto.LoginRequest;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.service.LoginSecurityService;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginSecurityService loginSecurityService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;


    @ApiOperation(value = "로그인 기능",
    notes = "세종대학교 api를 이용하여 로그인 합니다.")
    @PostMapping(value = "/login")
    public SuccessResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader("authorization", "*");

        loginSecurityService.doLogin(loginRequest.toSejongMember());
        log.info("{} : 로그인 성공하였습니다.", loginRequest.toSejongMember().getId());

        User user = userRepository.findByStudentNum(loginRequest.getId()).orElseThrow(NotFoundUserException::new);
        AuthUser authUser = new AuthUser(user);

        log.info("저장에 성공하였습니다.");

        return new SuccessResponse(jwtTokenProvider.createToken(authUser));
    }
    

}
