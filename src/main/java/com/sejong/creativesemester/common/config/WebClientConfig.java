package com.sejong.creativesemester.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//    @Bean
//    public ReactorResourceFactory resourceFactory(){
//        ReactorResourceFactory factory = new ReactorResourceFactory();
//        factory.setUseGlobalResources(false);
//        return factory;
//    }
//
//    @Bean
//    public WebClient webClient(){
//        return WebClient.builder()
//                .baseUrl("https://auth.imsejong.com/auth")
//                .build();
//    }
}
