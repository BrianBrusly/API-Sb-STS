package com.charlie_echo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Pratique pour une API REST
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/h2-console/**").permitAll() // Permet l'accès à la console H2
                .anyRequest().authenticated() // sécurise tous les autres endpoints
            )
            .httpBasic(withDefaults()) // Authentification basique
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // Autorise H2 Console
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                               .password(passwordEncoder().encode("admin"))
                               .roles("ADMIN")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }
}