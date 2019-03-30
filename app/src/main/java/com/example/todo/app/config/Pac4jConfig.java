package com.example.todo.app.config;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.credentials.UsernamePasswordCredentials;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.http.client.direct.DirectFormClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pac4jConfig {

	@Value("${pac4j.callback}")
	private String callback;
	private Authenticator<UsernamePasswordCredentials> authenticator;

	@Bean
	public Config config() {
		final DirectFormClient directFormClient = new DirectFormClient(authenticator);
		final Clients clients = new Clients(callback, directFormClient);
		return new Config(clients);
	}
}
