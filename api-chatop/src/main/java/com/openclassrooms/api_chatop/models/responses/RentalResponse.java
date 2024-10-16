package com.openclassrooms.api_chatop.models.responses;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RentalResponse {

    private Integer id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Integer owner_id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}