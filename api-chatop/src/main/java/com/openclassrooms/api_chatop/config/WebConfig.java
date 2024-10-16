package com.openclassrooms.api_chatop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Value("${upload.volume}") 
    private String UPLOAD_VOLUME_PATH;

    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configure les paramètres CORS pour permettre les requêtes de n'importe quelle origine
        registry.addMapping("/**")
        .allowedOriginPatterns("*")  
        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
        .allowedHeaders("*")         
        .allowCredentials(true);    
    }


    @Override
    public void addResourceHandlers(@NonNull ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**")
                .addResourceLocations("file:" + UPLOAD_VOLUME_PATH);
    }

}