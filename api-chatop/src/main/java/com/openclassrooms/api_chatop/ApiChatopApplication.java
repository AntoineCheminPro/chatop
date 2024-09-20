package com.openclassrooms.api_chatop;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "API ChatOp", version = "1.0", description = "API pour l'application ChatOp"))
@EnableWebSecurity
@EnableJpaRepositories("com.openclassrooms.api_chatop.repository")
@SpringBootApplication
@EnableJpaAuditing
public class ApiChatopApplication {
	private static final Logger log = LoggerFactory.getLogger(ApiChatopApplication.class);

	public static void main(String[] args) {
		System.setProperty("file.encoding", StandardCharsets.UTF_8.name());
		SpringApplication.run(ApiChatopApplication.class, args);
		log.info("Application started !");
	}

}
