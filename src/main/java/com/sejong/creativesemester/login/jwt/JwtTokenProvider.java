package com.sejong.creativesemester.login.jwt;

import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.dto.SejongMemberResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private static Key KEY;
    private long tokenValidTime = 60 * 60 * 1000;

    public JwtTokenProvider(
            @Value("${spring.jwt.secret-key}") String KEY
    ){
        this.KEY = Keys.hmacShaKeyFor(KEY.getBytes());
    }

    // accessToken 생성
    public String createAccessToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("studentNum", authUser.getUser().getStudentNum());
        claims.put("userName", authUser.getRole());
        claims.put("role", authUser.getRole());

        Date now = new Date(System.currentTimeMillis());
        String accessToken = Jwts.builder()
                .setSubject(authUser.getUser().getStudentNum())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(KEY)
                .compact();

        return accessToken;
    }

    // refreshToken 생성
    public String createRefreshToken(AuthUser authUser){
        Map<String, Object> claims = new HashMap<>();
        claims.put("studentNum", authUser.getUser().getStudentNum());
        claims.put("userName", authUser.getUsername());
        claims.put("role", authUser.getRole());

        Date now = new Date(System.currentTimeMillis());
        String refreshToken = Jwts.builder()
                .setSubject(authUser.getUser().getStudentNum())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(KEY)
                .compact();

        return refreshToken;
    }

    // 인증 하기
    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("studentNum").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    // jwt에서 token 추출
    public Claims getUserPK(String token){
        return Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token).getBody();
    }


    // 유효성 및 만료 날짜 확인
   public boolean validationToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(KEY).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }
}
