package com.sejong.creativesemester.login.service;


import com.sejong.creativesemester.common.domain.Helper;
import com.sejong.creativesemester.common.format.exception.login.NoAuthException;
import com.sejong.creativesemester.common.format.exception.login.NoRefreshTokenException;
import com.sejong.creativesemester.common.format.exception.login.NoValidTokenException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.domain.RefreshToken;
import com.sejong.creativesemester.login.dto.TokenRequest;
import com.sejong.creativesemester.login.dto.sejong.SejongMemberRequestDto;
import com.sejong.creativesemester.login.dto.sejong.SejongMemberResponseDto;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import com.sejong.creativesemester.login.jwt.TokenInfo;
import com.sejong.creativesemester.login.repository.RefreshTokenRepository;
import com.sejong.creativesemester.major.entity.Major;
import com.sejong.creativesemester.major.repository.MajorRepository;
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
import java.util.Date;
import java.util.concurrent.TimeUnit;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginSecurityService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final MajorRepository majorRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;


    // login 했을 때 정보들 가져오기
    public TokenInfo doLogin(SejongMemberRequestDto sejongMemberRequestDto, HttpServletRequest httpServletRequest){

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

        // major 찾기, 없으면 새로 생성
        Major major = majorRepository.findByName(memberDto.getResult().getBody().getMajor()).orElseGet(
                () -> majorRepository.save(Major.builder()
                                .name(memberDto.getResult().getBody().getMajor())
                        .build())
        );

        // 세종 api로 로그인한 정보 userRepository에 없으면 저장.
        User user = userRepository.findByStudentNum(sejongMemberRequestDto.getId()).orElseGet(
                () -> userRepository.save(User.builder()
                                .studentNum(sejongMemberRequestDto.getId())
                                .name(memberDto.getResult().getBody().getName())
                                .grade(Integer.parseInt(memberDto.getResult().getBody().getGrade()))
                                .major(major)
                                .role(Role.ROLE_USER)
                                .status(memberDto.getResult().getBody().getStatus())
                        .build())
        );
        AuthUser authUser = new AuthUser(user);

        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authUser, Helper.getClientIp(httpServletRequest));
        tokenInfo.setRole(authUser.getRole());
        tokenInfo.setName(authUser.getStudentNum());

        redisTemplate.opsForValue()
                .set("RefreshToken:" + authUser.getStudentNum(), tokenInfo.getRefreshToken(),
                        tokenInfo.getRefreshTokenExpiration() - new Date().getTime(), TimeUnit.MILLISECONDS);

        refreshTokenRepository.save(RefreshToken.builder()
                .id(authUser.getStudentNum())
                .ip(Helper.getClientIp(httpServletRequest))
                .refreshToken(tokenInfo.getRefreshToken())
                .expiration(tokenInfo.getRefreshTokenExpiration())
                .build());



        return tokenInfo;
    }


    // 토큰
    public Object reissueToken(HttpServletRequest request){
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        String accessToken = jwtTokenProvider.resolveAccessToken(request);

        if(jwtTokenProvider.getExpiration(accessToken)>0){
            return "reissue not required";
        }
        if(jwtTokenProvider.validationToken(refreshToken) && jwtTokenProvider.isRefreshToken(refreshToken)){
            User user = userRepository.findByStudentNum(jwtTokenProvider.isUserPK(refreshToken)).orElseThrow(NotFoundUserException::new);
            AuthUser authUser = new AuthUser(user);
            log.info("reissue: {}, {}", authUser.getUsername(), authUser.getRole());

            String currentIp = Helper.getClientIp(request);
            RefreshToken refreshToken1 = refreshTokenRepository.findById(authUser.getStudentNum()).orElseThrow(NoRefreshTokenException::new);
            log.info("currentIp: {}", currentIp);
            if(refreshToken1.getIp().equals(currentIp)) {
                TokenInfo tokenInfo = jwtTokenProvider.generateToken(authUser, currentIp);
                redisTemplate.opsForValue().set("RefreshToken:" + authUser.getStudentNum(), tokenInfo.getRefreshToken(),
                        tokenInfo.getRefreshTokenExpiration(), TimeUnit.MILLISECONDS);

                refreshTokenRepository.save(RefreshToken.builder()
                        .id(authUser.getStudentNum())
                        .ip(currentIp)
                        .refreshToken(tokenInfo.getRefreshToken())
                        .expiration(tokenInfo.getRefreshTokenExpiration()).build());

                tokenInfo.setRole(authUser.getRole());
                tokenInfo.setName(authUser.getUsername());
                return tokenInfo;
            }
        }
        throw new NoValidTokenException();
    }

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
