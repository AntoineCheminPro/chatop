package com.openclassrooms.api_chatop.dto;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.api_chatop.model.Messages;
import com.openclassrooms.api_chatop.model.Rentals;
import com.openclassrooms.api_chatop.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "UserDTO")
public class UserDTO {
    @Schema(description = "User ID")
    private Integer id;
    @Schema(description = "User Name")
    private String name;
    @Schema(description = "User Email")
    private String email;
    @Schema(description = "User Password")
    private String password;
    @Schema(description = "User Created At")
    private String created_at;
    @Schema(description = "User Updated At")
    private String updated_at;

    @JsonIgnore
    private List<Messages> messages;
    @JsonIgnore
    private List<Rentals> rentals;

    public static UserDTO fromEntity(User user) {
        return UserDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .password(user.getPassword())
        .created_at(user.getCreated_at().toString())
        .updated_at(user.getUpdated_at().toString())
        .build();
    }

    public User toEntity(UserDTO userDTO) {
        if(userDTO == null){
            throw new IllegalArgumentException("userDTO ne peut pas être nul");
        }

        User user = new User();
        if (userDTO.getId() == null) {
            user.setCreated_at(Instant.now());
        }
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreated_at(Instant.parse(userDTO.getCreated_at()));
        user.setUpdated_at(Instant.parse(userDTO.getUpdated_at()));
        return user;
    }
}
