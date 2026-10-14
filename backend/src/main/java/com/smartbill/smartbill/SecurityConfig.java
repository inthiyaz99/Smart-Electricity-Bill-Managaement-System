
package com.smartbill.smartbill;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final List<String> allowedOrigins;

    public SecurityConfig(
            @Value("#{'${app.cors.allowed-origins}'.split(',')}") List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins.stream().map(String::trim).toList();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(allowedOrigins);

        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        config.setAllowedHeaders(List.of("Content-Type", "Authorization", "Accept"));
        config.setAllowCredentials(false);
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource) {
        return new CorsFilter(corsConfigurationSource);
    }
}
