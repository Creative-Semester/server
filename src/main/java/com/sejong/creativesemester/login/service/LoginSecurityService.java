package com.sejong.creativesemester.login.service;


import com.sejong.creativesemester.common.domain.Helper;
import com.sejong.creativesemester.common.config.format.exception.login.NoAuthException;
import com.sejong.creativesemester.common.config.format.exception.login.NoValidTokenException;
import com.sejong.creativesemester.common.config.format.exception.user.NotFoundUserException;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginSecurityService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;


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
    public ResponseEntity<?> reissueToken(HttpServletRequest request){
        String token = jwtTokenProvider.resolveRefreshToken(request);
        log.info("validation : {}, ifRefresh : {}", jwtTokenProvider.validationToken(token), jwtTokenProvider.isRefreshToken(token));

        if(jwtTokenProvider.validationToken(token) && jwtTokenProvider.isRefreshToken(token)){
            User user = userRepository.findByStudentNum(jwtTokenProvider.isUserPK(token)).orElseThrow(NotFoundUserException::new);
            AuthUser authUser = new AuthUser(user);
            if(!(authUser.getStudentNum()).equals(jwtTokenProvider.isUserPK(token))) {
                throw new NotFoundUserException();
            }
            else{
                String currentIp = Helper.getClientIp(request);
                RefreshToken refreshToken = refreshTokenRepository.findById(authUser.getStudentNum()).orElseThrow(NotFoundUserException::new);
                log.info("currentIp: {}", currentIp);
                if(refreshToken.getIp().equals(currentIp)) {
                    TokenInfo tokenInfo = jwtTokenProvider.generateToken(authUser, currentIp);

                    redisTemplate.opsForValue().set("RefreshToken:" + authUser.getStudentNum(), tokenInfo.getRefreshToken(),
                            tokenInfo.getRefreshTokenExpiration(), TimeUnit.MILLISECONDS);
                    
                    refreshTokenRepository.save(RefreshToken.builder()
                            .id(authUser.getStudentNum())
                                    .ip(currentIp)
                            .refreshToken(tokenInfo.getRefreshToken())
                            .expiration(tokenInfo.getRefreshTokenExpiration()).build());
                    return ResponseEntity.ok(tokenInfo);
                }
            }
        }
        throw new NoValidTokenException();
    }

    @Transactional
    public ResponseEntity<?> doLogout(HttpServletRequest httpServletRequest){
        String accessToken = jwtTokenProvider.resolveAccessToken(httpServletRequest);
        log.info("accessToken: {}", accessToken);

        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        String id = jwtTokenProvider.isUserPK(accessToken);
        log.info("pk: {}", id);

        redisTemplate.opsForValue().set(accessToken, "logout", expiration, TimeUnit.MILLISECONDS);
        refreshTokenRepository.deleteById(id);

        return ResponseEntity.ok("로그아웃되었습니다.");
    }


}
