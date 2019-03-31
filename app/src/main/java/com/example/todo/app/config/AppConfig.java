package com.example.todo.app.config;

import org.pac4j.core.credentials.password.JBCryptPasswordEncoder;
import org.pac4j.core.credentials.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder( ) {
        return new JBCryptPasswordEncoder(); 
    }
}
