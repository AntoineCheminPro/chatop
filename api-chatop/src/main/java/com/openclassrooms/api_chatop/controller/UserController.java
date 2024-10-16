package com.openclassrooms.api_chatop.controller;


import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api_chatop.models.responses.UserResponse;
import com.openclassrooms.api_chatop.services.UserService;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable("id") final Integer id) {
        var user = userService.getUserById(id);

        // Create a custom formatter with the desired pattern
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

        // Format the LocalDate to a string
        String formattedDateString = user.getCreatedAt().format(dateTimeFormatter);

        var rentalDto = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(formattedDateString)
                .build();
        return new ResponseEntity<>(rentalDto, HttpStatus.OK);
    }
}
