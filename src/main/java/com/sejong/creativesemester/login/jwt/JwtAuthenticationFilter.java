package com.sejong.creativesemester.login.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!ObjectUtils.isEmpty(accessToken) && accessToken.startsWith("Bearer ")){
            accessToken = accessToken.substring(7, accessToken.length());
        }

        if(accessToken != null && jwtTokenProvider.validationToken(accessToken)){
            //토큰으로부터 유저 정보 받기
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

            // 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 '{}' 정보를 저장 완료 하였습니다.", authentication.getName());
        }

        filterChain.doFilter(request, response);
    }
}
