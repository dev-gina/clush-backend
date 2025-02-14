package com.example.calendarapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // 모든 요청 인증 없이 허용
            )
            .httpBasic().disable()   // Basic Auth 비활성화
            .formLogin().disable();  // 폼 로그인 비활성화
        return http.build();
    }
}
