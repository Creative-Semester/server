package com.sejong.creativesemester.login.service;


import com.sejong.creativesemester.common.format.exception.login.NoAuthException;
import com.sejong.creativesemester.login.dto.LoginRequestDto;
import com.sejong.creativesemester.login.dto.SejongMemberResponseDto;
import com.sejong.creativesemester.user.entity.Role;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginSecurityService {

    private final WebClient webClient;
    private final UserRepository userRepository;

    // login 했을 때 정보들 가져오기
    public SejongMemberResponseDto doLogin(LoginRequestDto loginRequestDto){
        SejongMemberResponseDto memberDto = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("method", "ClassicSession")
                        .build())
                .body(BodyInserters.fromValue(loginRequestDto))
                .retrieve()
                .bodyToMono(SejongMemberResponseDto.class)
                .block();

        log.info(memberDto.toString());


        // 권한 확인
        if(memberDto.getResult().is_auth() == false){
            throw new NoAuthException();
        }

        User user = userRepository.findByStudentNum(loginRequestDto.getId()).orElseGet(
                () -> userRepository.save(User.builder()
                                .studentNum(loginRequestDto.getId())
                                .name(memberDto.getResult().getBody().getName())
                                .grade(Integer.parseInt(memberDto.getResult().getBody().getGrade()))
                                .role(Role.ROLE_USER)
                                .status(memberDto.getResult().getBody().getStatus())
                        .build())
        );

        return memberDto;
    }

    // 다음 서비스에 AuthUser나 User에 정보를 집어넣는 메서드 구현?
}
