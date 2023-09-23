package com.sejong.creativesemester.login.service;


import com.sejong.creativesemester.common.format.exception.login.NoAuthException;
import com.sejong.creativesemester.login.dto.SejongMemberRequestDto;
import com.sejong.creativesemester.login.dto.SejongMemberResponseDto;
import com.sejong.creativesemester.user.entity.Role;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.Logger;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class LoginSecurityService {

    private final UserRepository userRepository;

    // login 했을 때 정보들 가져오기
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

        userRepository.findByStudentNum(sejongMemberRequestDto.getId()).orElseGet(
                () -> userRepository.save(User.builder()
                                .studentNum(sejongMemberRequestDto.getId())
                                .name(memberDto.getResult().getBody().getName())
                                .grade(Integer.parseInt(memberDto.getResult().getBody().getGrade()))
                                .role(Role.ROLE_USER)
                                .status(memberDto.getResult().getBody().getStatus())
                        .build())
        );

        return memberDto;
    }



}
