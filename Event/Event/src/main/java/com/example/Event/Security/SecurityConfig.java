package com.example.Event.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // In-memory users for simplicity; in real apps, load users from DB.
        return new InMemoryUserDetailsManager(
                org.springframework.security.core.userdetails.User
                        .withUsername("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .roles("ADMIN")
                        .build(),
                org.springframework.security.core.userdetails.User
                        .withUsername("coach")
                        .password(passwordEncoder.encode("coach123"))
                        .roles("COACH")
                        .build()
        );
    }

    @Bean
    public HttpSecurity configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/api/events/postEvent", "/api/events/update/**", "/api/events/delete/**")
                .hasRole("ADMIN")  // Only admins can modify or delete events
                .requestMatchers("/api/events/GetAllEvent", "/api/events/GetEvent/**")
                .hasAnyRole("ADMIN", "COACH")  // Both coaches and admins can view events
                .anyRequest().authenticated()
                .and()
                .httpBasic();  // Use basic authentication for simplicity

        return http;
    }
}

