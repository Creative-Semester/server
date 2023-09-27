package com.sejong.creativesemester.login.service;


import antlr.Token;
import com.sejong.creativesemester.common.domain.Helper;
import com.sejong.creativesemester.common.format.exception.login.NoAuthException;
import com.sejong.creativesemester.common.format.exception.login.NoRefreshTokenException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.domain.RefreshToken;
import com.sejong.creativesemester.login.dto.sejong.SejongMemberRequestDto;
import com.sejong.creativesemester.login.dto.sejong.SejongMemberResponseDto;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.jwt.TokenInfo;
import com.sejong.creativesemester.login.repository.RefreshTokenRepository;
import com.sejong.creativesemester.user.entity.Role;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginSecurityService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;


    // login 했을 때 정보들 가져오기
    @Transactional
    public SejongMemberResponseDto doLogin(SejongMemberRequestDto sejongMemberRequestDto){

//        sejongMemberRequestDto.setId("19011729");
//        sejongMemberRequestDto.setPw("clark1245!");

        WebClient webClient = WebClient.builder()
                .baseUrl("https://auth.imsejong.com/auth").build();

        log.info("넘기는중");
        SejongMemberResponseDto memberDto = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("method","ClassicSession")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(sejongMemberRequestDto)
                .retrieve()
                .bodyToMono(SejongMemberResponseDto.class)
                .block();

        log.info("됐나요?");
        // 권한 확인
        if(memberDto.getResult().getIs_auth() == "false"){
            throw new NoAuthException();
        }

        // 세종 api로 로그인한 정보 userRepository에 없으면 저장.
        User user = userRepository.findByStudentNum(sejongMemberRequestDto.getId()).orElseGet(
                () -> userRepository.save(User.builder()
                                .studentNum(sejongMemberRequestDto.getId())
                                .name(memberDto.getResult().getBody().getName())
                                .grade(Integer.parseInt(memberDto.getResult().getBody().getGrade()))
                                .role(Role.ROLE_USER)
                                .status(memberDto.getResult().getBody().getStatus())
                        .build())
        );
        AuthUser authUser = new AuthUser(user);


        return memberDto;
    }


    // 재발급 서비스 구현 시 return 할 클래스?
    // 토큰
    @Transactional
    public TokenInfo reissueToken(HttpServletRequest request){
        String token = jwtTokenProvider.resolveRefreshToken(request);
        log.info("{}", token);

        if(token != null && jwtTokenProvider.validationToken(token)){
            if(jwtTokenProvider.isRefreshToken(token) == true){
                RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(request.getParameter("refreshToken")).orElseThrow(NoRefreshTokenException::new);
                if(refreshToken != null){
                    String nowIp = Helper.getClientIp(request);
                    if(refreshToken.getIp().equals(nowIp)){
                        User user = userRepository.findByStudentNum(refreshToken.getStudentNum()).orElseThrow(NotFoundUserException::new);
                        AuthUser authUser = new AuthUser(user);

                        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authUser);

                        refreshTokenRepository.save(RefreshToken.builder()
                                .studentNum(authUser.getStudentNum())
                                .userName(authUser.getUsername())
                                .role(authUser.getRole())
                                .ip(nowIp)
                                .refreshToken(tokenInfo.getRefreshToken())
                                .build());

                        log.info("authUser: {}, {}", authUser.getStudentNum(), authUser.getUsername());
                        return tokenInfo;
                    }
                }
            }
        }

        return null;
    }


}
