package com.example.calendarapi.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/public/**")  // 공개 API 경로 설정
                .build();
    }

    @Bean
    public GroupedOpenApi secureApi() {
        return GroupedOpenApi.builder()
                .group("secure")
                .pathsToMatch("/api/secure/**")  // 보안 API 경로 설정
                .build();
    }
}
