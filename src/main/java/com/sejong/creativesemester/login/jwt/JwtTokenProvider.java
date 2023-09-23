package com.sejong.creativesemester.login.jwt;

import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.dto.SejongMemberResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
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
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static Key key;
    private long tokenValidTime = 60 * 60 * 1000;

    public JwtTokenProvider(
            @Value("${spring.jwt.key}") String KEY
    ){
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // accessToken 생성
    public String createToken(AuthUser authUser){
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
                .signWith(key)
                .compact();

        return accessToken;
    }

    // 인증 하기
    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("studentNum").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public String resolveToken(HttpServletRequest request){
        if(request.getHeader("authorization") != null)
            return request.getHeader("authorization").substring(7);
        return null;
    }

    // 유효성 및 만료 날짜 확인
   public Boolean validationToken(String token){
        try{
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }
}
