package com.demo.social.config;


import com.demo.social.security.JwtFilter;
import com.demo.social.security.oauth2.Oauth2LoginFailureHandler;
import com.demo.social.security.oauth2.Oauth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;
    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;
    private final Oauth2LoginFailureHandler oauth2LoginFailureHandler;

    private static final String[] AUTH_WHITE_LIST = {"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    @Bean
    public SecurityFilterChain jwtAuthorizationSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JWT doesn't need sessions
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(AUTH_WHITE_LIST).permitAll()
                                .anyRequest().permitAll()
                )
                // Add the JWT filter before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Enable OAuth2 login
                .oauth2Login(customizer -> customizer
                        .successHandler(oauth2LoginSuccessHandler)
                        .failureHandler(oauth2LoginFailureHandler)
                );
        return http.build();
    }


}