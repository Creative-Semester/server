package com.sejong.creativesemester.login.jwt;

import com.sejong.creativesemester.login.domain.AccessToken;
import com.sejong.creativesemester.login.domain.AuthUser;
import com.sejong.creativesemester.login.domain.RefreshToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private static Key key;
    private long tokenValidTime = 60 * 60 * 1000; // 1hours
    private final String BEARER_FREFIX = "Bearer ";

    public JwtTokenProvider(
            @Value("${spring.jwt.key}") String KEY
    ){
        byte[] keyBytes = Decoders.BASE64.decode(KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenInfo generateToken(AuthUser authUser, String ip){

        AccessToken token1 = createAccessToken(authUser);
        RefreshToken token2 = createRefreshToken(authUser, ip);

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(token1.getAccessToken())
                .refreshToken(token2.getRefreshToken())
                .accessTokenExpiration(token1.getExpiration())
                .refreshTokenExpiration(token2.getExpiration())
                .build();
    }

    // accessToken 생성
    public AccessToken createAccessToken(AuthUser authUser){
        Claims claims = Jwts.claims().setSubject(authUser.getStudentNum());
        claims.put("username", authUser.getUsername());
        claims.put("role", authUser.getRole());


        Date now = new Date(System.currentTimeMillis());
        String token = BEARER_FREFIX+ Jwts.builder()
                .setClaims(claims)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+tokenValidTime))
                .signWith(key)
                .compact();
        Long expiration = now.getTime()+tokenValidTime;

        return new AccessToken(token, expiration);
    }

    public RefreshToken createRefreshToken(AuthUser authUser, String ip){

        Date now = new Date(System.currentTimeMillis());
        String token = BEARER_FREFIX + Jwts.builder()
                .setSubject(authUser.getStudentNum())
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+(tokenValidTime*24*14))) // 2 weeks
                .signWith(key)
                .compact();
        Long expiration = now.getTime()+(tokenValidTime*24*14);

        return new RefreshToken(authUser.getStudentNum(), ip, token, expiration);
    }


    // 인증 하기
    public Authentication getAuthentication(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        log.info("username: {}", claims.get("username"));

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(new String[]{ claims.get("role").toString()})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        log.info("authorities: {}", authorities);

        User principal = new User(claims.getSubject(), "", authorities);
        log.info("principal: {}", principal.getUsername());

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);

    }

    public String resolveToken(String token){
        if(!ObjectUtils.isEmpty(token) && token.startsWith("Bearer ")){
                return token.substring(7);
        }
        return null;
    }

    public String resolveHttpToken(HttpServletRequest httpServletRequest){
        String token = httpServletRequest.getParameter("accessToken");
        if(!ObjectUtils.isEmpty(token) && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }

   // 유효성 및 만료 날짜 확인
   public Boolean validationToken(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        if(!StringUtils.hasText(token)){
            return false;
        }
        return !claims.getExpiration().before(new Date());
    }

    // refresh 토큰인지 확인
    public Boolean isRefreshToken(String token){
        String type = (String) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("type");
        return type.equals("refresh");
    }

    // jwt 토큰 만료시간
    public Long getExpiration(String accessToken){
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();

        long now = new Date().getTime();
        return expiration.getTime()-now;
    }

    public String isUserPK(String token){
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}