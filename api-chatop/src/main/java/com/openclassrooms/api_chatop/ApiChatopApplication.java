package com.openclassrooms.api_chatop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.openclassrooms.api_chatop.models.Role;
import com.openclassrooms.api_chatop.repositories.RoleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(title = "API ChatOp", version = "1.0", description = "API pour l'application ChatOp"))
@EnableWebSecurity
@EnableJpaRepositories("com.openclassrooms.api_chatop.repositories")
@SpringBootApplication
public class ApiChatopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiChatopApplication.class, args);
    }

@Bean
    public CommandLineRunner runner (RoleRepository roleRepository) {
        return args -> {
                if(roleRepository.findByName("USER").isEmpty()) {
                    roleRepository.save(Role.builder().name("USER").build());
                }
        };
    }
}

