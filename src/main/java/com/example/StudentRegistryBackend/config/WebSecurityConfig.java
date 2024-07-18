/*package com.example.StudentRegistryBackend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/student/**").permitAll() // Allow unauthenticated access to /student and its sub-paths
                                .anyRequest().authenticated() // Require authentication for other requests
                )
                .httpBasic(withDefaults()) // Use basic HTTP authentication (if you want to use a simple form login)
                .formLogin(withDefaults()); // Enable form login if needed, configure with defaults

        return http.build();
    }
}*/
