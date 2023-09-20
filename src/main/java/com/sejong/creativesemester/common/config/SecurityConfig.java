package com.sejong.creativesemester.common.config;

import com.sejong.creativesemester.login.jwt.JwtAuthenticationFilter;
import com.sejong.creativesemester.login.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig{


    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsService userDetailsService;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{



        // Cross-Site HTTP Request를 받기 위한 설정
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .configurationSource(configurationSource())
                .and()
                .sessionManagement() // 다중 세션 로그인 유무
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/api/auth/expired");

        http
                .authorizeRequests()
                .antMatchers("swagger-ui/**", "/api/auth/login").permitAll()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin() // 로그인 http
                .loginPage("/api/auth/login").permitAll()
                .and()
                .logout() // 로그아웃 http
                .permitAll()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean // pw 암호화 처리
    public BCryptPasswordEncoder encoderPw(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedOrigins(List.of("*"));
        cors.setAllowedMethods(List.of("POST","PUT","PATCH","DELETE","GET","OPTIONS"));
        cors.setAllowedHeaders(List.of("*"));
        cors.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cors);
        return source;
    }

}
