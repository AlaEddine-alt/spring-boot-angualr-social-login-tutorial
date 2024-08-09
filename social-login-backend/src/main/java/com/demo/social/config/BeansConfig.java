package com.demo.social.config;

import com.demo.social.config.audit.ApplicationAuditAware;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    @Value("${application.cors.allowed-origins:*}")
    private List<String> allowedOrigins;

    @Bean
    public AuditorAware<String> auditorAware() {
        return new ApplicationAuditAware();
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        // Allow credentials (cookies, authorization headers, etc.) to be included in requests
        config.setAllowCredentials(true);

        // Set the allowed origins for CORS requests
        config.setAllowedOrigins(allowedOrigins);

        // Allow all headers in CORS requests
        config.setAllowedHeaders(List.of("*"));

        // Allow all HTTP methods in CORS requests
        config.setAllowedMethods(List.of("*"));

        // Register the CORS configuration for all paths
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
