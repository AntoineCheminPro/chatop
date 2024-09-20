package com.openclassrooms.api_chatop.dto;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.api_chatop.model.Rentals;
import com.openclassrooms.api_chatop.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "RentalsDTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalsDTO {
    @Schema(description = "Rentals ID")
    private Integer id;
    @Schema(description = "Rentals Created At")
    private String createdAt;
    @Schema(description = "Rentals Updated At")
    private String updatedAt;
    @Schema(description = "Rentals Name")
    private String name;
    @Schema(description = "Rentals Surface")
    private Integer surface;
    @Schema(description = "Rentals Price")
    private Integer price;
    @Schema(description = "Rentals Description")
    private String description;
    @Schema(description = "Rentals Picture")
    private String picture;
    @Schema(description = "Rentals Owner ID")
    private Integer owner_id;

    @JsonIgnore 
    private List<MessagesDTO> messages;

    public static RentalsDTO fromEntity(Rentals rentals) {
        if (rentals == null) {
            throw new IllegalArgumentException("Rentals ne peut pas être nul");
        }
        return RentalsDTO.builder()
        .id(rentals.getId())
        .createdAt(rentals.getCreated_at().toString())
        .updatedAt(rentals.getUpdated_at().toString())
        .name(rentals.getName())
        .surface(rentals.getSurface())
        .price(rentals.getPrice())
        .description(rentals.getDescription())
        .picture(rentals.getPicture())
        .owner_id(rentals.getOwner_id().getId())
        .build();
    }

    public Rentals toEntity(RentalsDTO rentalsDTO) {
        if (rentalsDTO == null) {
            throw new IllegalArgumentException("RentalsDTO ne peut pas être nul");
        }
        Rentals rentals = new Rentals();
        rentals.setId(rentalsDTO.getId());
        
        if (rentalsDTO.getId() == null) {
            rentals.setCreated_at(Instant.now());
        }

        rentals.setUpdated_at(Instant.now());
        rentals.setName(rentalsDTO.getName());
        rentals.setSurface(rentalsDTO.getSurface());
        rentals.setPrice(rentalsDTO.getPrice());
        rentals.setDescription(rentalsDTO.getDescription());
        rentals.setPicture(rentalsDTO.getPicture());
        rentals.setOwner_id(new User(rentalsDTO.getOwner_id()));
        return rentals;
    }
}
