package com.openclassrooms.api_chatop.models.requests;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RentalRequest {

    @NotBlank
    private String name;
    @NotNull
    private Double surface;
    @NotNull
    private Double price;
    private MultipartFile picture;
    @NotBlank
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
