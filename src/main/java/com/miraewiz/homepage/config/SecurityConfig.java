package com.miraewiz.homepage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Development convenience, enable for prod if needed
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin", "/admin/**", "/api/admin/**").authenticated() // Admin pages and APIs protected
                .anyRequest().permitAll() // All other pages open
            )
            .formLogin(form -> form
                .loginPage("/admin/login") // Hidden admin login
                .loginProcessingUrl("/admin/login-process")
                .defaultSuccessUrl("/admin/reviews", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
