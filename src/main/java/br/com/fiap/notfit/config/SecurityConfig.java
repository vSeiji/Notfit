package br.com.fiap.notfit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .oauth2Login(form -> form.loginPage("/login").defaultSuccessUrl("/exercicio").permitAll())
        .logout( logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
        .build();
    }
    
}
