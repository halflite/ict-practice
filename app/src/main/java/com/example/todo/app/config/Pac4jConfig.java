package com.example.todo.app.config;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.direct.DirectFormClient;
import org.pac4j.http.client.indirect.FormClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.todo.app.auth.SigninClient;
import com.example.todo.app.service.SigninAuthenticator;

@Configuration
public class Pac4jConfig {

    @Value("${pac4j.callback}")
    private String callback;
    private SigninAuthenticator signinAuthenticator;

    @Bean
    public Config config() {
        final SigninClient signinClient = new SigninClient(signinAuthenticator);
        final Clients clients = new Clients(callback, signinClient);
        return new Config(clients);
    }

    @Autowired
    public void setSigninAuthenticator(SigninAuthenticator signinAuthenticator) {
        this.signinAuthenticator = signinAuthenticator;
    }
}
