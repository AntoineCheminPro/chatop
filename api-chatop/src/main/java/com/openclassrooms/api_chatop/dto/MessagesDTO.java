package com.openclassrooms.api_chatop.dto;
import java.time.Instant;

import com.openclassrooms.api_chatop.model.Messages;
import com.openclassrooms.api_chatop.model.Rentals;
import com.openclassrooms.api_chatop.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "MessagesDTO")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagesDTO {
    @Schema(description = "Messages ID")
    private Integer id;
    @Schema(description = "Messages Message")
    private String message;
    @Schema(description = "Messages User ID")
    private Integer user;
    @Schema(description = "Messages Rental ID")
    private Integer rental;
    @Schema(description = "Messages Created At")
    private Instant createdAt;
    @Schema(description = "Messages Updated At")
    private Instant updatedAt;


    public static MessagesDTO fromEntity(Messages messages){
        if(messages == null){
            throw new IllegalArgumentException("Messages ne peut pas être nul");
        }
        return MessagesDTO.builder()
        .id(messages.getId())
        .message(messages.getMessage())
        .user(messages.getUser().getId())
        .rental(messages.getRental().getId())
        .createdAt(messages.getCreatedAt()) 
        .updatedAt(messages.getUpdatedAt())
        .build();
    }

    public Messages toEntity(MessagesDTO messagesDTO){
        if(messagesDTO == null){
            throw new IllegalArgumentException("messagesDTO ne peut pas être nul");
        }

        Messages messages = new Messages();
        if (messagesDTO.getId() == null) {
            messages.setCreatedAt(Instant.now());
        }

        messages.setId(messagesDTO.getId());
        messages.setMessage(messagesDTO.getMessage());
        messages.setUser(new User(messagesDTO.getUser()));
        messages.setRental(new Rentals(messagesDTO.getRental()));
        messages.setCreatedAt(messagesDTO.getCreatedAt() != null ? messagesDTO.getCreatedAt() : Instant.now()); 
        messages.setUpdatedAt(messagesDTO.getUpdatedAt() != null ? messagesDTO.getUpdatedAt() : Instant.now()); 
        return messages;
    }
}


