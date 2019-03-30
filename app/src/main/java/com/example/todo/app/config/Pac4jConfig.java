package com.example.todo.app.config;

import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pac4jConfig {

	@Value("${pac4j.callback}")
	private String callback;

	@Bean
	public Config config() {
		final Clients clients = new Clients(callback);
		return new Config(clients);
	}
}
