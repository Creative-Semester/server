package com.sejong.creativesemester.common.domain;

import javax.servlet.http.HttpServletRequest;

public class Helper {

    public static String getClientIp(HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");

        if(ip == null)
            ip = request.getRemoteAddr();

        return ip;
    }
}
