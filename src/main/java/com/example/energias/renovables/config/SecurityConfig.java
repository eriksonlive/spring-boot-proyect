package com.example.energias.renovables.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
            .csrf(csrf -> csrf.disable())
            .httpBasic(basic -> basic.disable()) // Desactiva la autenticación básica
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().permitAll() // O configura las rutas según tus necesidades
        );
        return http.build();
    }
}

