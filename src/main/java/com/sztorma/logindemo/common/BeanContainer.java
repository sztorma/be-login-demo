package com.sztorma.logindemo.common;

import com.sztorma.logindemo.security.JwtTokenUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanContainer {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.secret.captcha}")
    private String captchaSecret;

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil(jwtSecret);
    }

    @Bean
    public JwtTokenUtil captchaTokenUtil() {
        return new JwtTokenUtil(captchaSecret);
    }

}
