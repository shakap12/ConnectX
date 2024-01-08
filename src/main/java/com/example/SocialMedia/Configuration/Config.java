package com.example.SocialMedia.Configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class Config {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests((req)->
                req.requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated());
        http.sessionManagement((management)->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class);
        http.csrf((c)->c.disable());
        http.csrf(csrf-> csrf.disable());
        http.cors(cors->cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }
    private CorsConfigurationSource corsConfigurationSource(){
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg=new CorsConfiguration();
                cfg.setAllowedOrigins(Arrays.asList("http://localhost:8081/"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowCredentials(true);
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
