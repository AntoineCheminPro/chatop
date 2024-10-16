package com.openclassrooms.api_chatop.models.requests;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageRequest {

    private String message;
    private Integer user_id;
    private Integer rental_id;
}
