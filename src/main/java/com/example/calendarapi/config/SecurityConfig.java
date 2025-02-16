package com.example.calendarapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (POST 요청 문제 방지)
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 적용
            .authorizeHttpRequests(auth -> auth
                // ✅ 일정 CRUD (모든 사용자 허용)
                .requestMatchers(HttpMethod.GET, "/api/events/**").permitAll()   // 조회 허용
                .requestMatchers(HttpMethod.POST, "/api/events/**").permitAll()  // 등록 허용
                .requestMatchers(HttpMethod.PUT, "/api/events/**").permitAll()   // 수정 허용
                .requestMatchers(HttpMethod.DELETE, "/api/events/**").permitAll() // 삭제 허용

                // ✅ Swagger 문서 및 에러 페이지 허용
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/index.html",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/error"
                ).permitAll()

                // 🔐 기타 요청은 인증 필요
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 없이 JWT 방식 사용
            .headers(headers -> headers.frameOptions(frame -> frame.disable())); // iframe 관련 보안 해제

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // 모든 도메인 허용
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // CRUD 요청 허용
        configuration.setAllowedHeaders(List.of("*")); // 모든 헤더 허용
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true); // 쿠키 및 인증 정보 허용
        configuration.setMaxAge(3600L); // 1시간 동안 캐시

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
