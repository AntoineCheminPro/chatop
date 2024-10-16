package com.openclassrooms.api_chatop.controller;

import com.openclassrooms.api_chatop.models.Message;
import com.openclassrooms.api_chatop.models.requests.MessageRequest;
import com.openclassrooms.api_chatop.models.responses.SimpleOutputMessageResponse;
import com.openclassrooms.api_chatop.services.MessageService;
import com.openclassrooms.api_chatop.services.RentalService;
import com.openclassrooms.api_chatop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("messages")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final RentalService rentalService;

    public MessageController(MessageService messageService,
                             UserService userService,
                             RentalService rentalService) {
        this.messageService = messageService;
        this.userService = userService;
        this.rentalService = rentalService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMessage(@Valid @RequestBody MessageRequest messageRequest) {
        var user = userService.getUserById(messageRequest.getUser_id());
        var rental = rentalService.getRental(messageRequest.getRental_id());

        var message = Message.builder()
                .message(messageRequest.getMessage())
                .user(user)
                .rental(rental)
                .build();
        messageService.save(message);
        return new ResponseEntity<>(new SimpleOutputMessageResponse("Message sent with success !"), HttpStatus.CREATED);
    }
}
