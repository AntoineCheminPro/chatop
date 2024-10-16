package com.openclassrooms.api_chatop.controller;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.openclassrooms.api_chatop.models.requests.LoginUserRequest;
import com.openclassrooms.api_chatop.models.requests.RegisterUserRequest;
import com.openclassrooms.api_chatop.models.responses.AuthenticationResponse;
import com.openclassrooms.api_chatop.models.responses.UserResponse;
import com.openclassrooms.api_chatop.services.AuthenticationService;
import com.openclassrooms.api_chatop.services.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthenticationController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        var authenticationResponse = authenticationService.registerUser(registerUserRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        var authenticationResponse = authenticationService.loginUser(loginUserRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserResponse> getCurrentUser(WebRequest request) {
        var userEmail = (String) request.getAttribute("currentUser_email", WebRequest.SCOPE_REQUEST);
        var user = userService.getUserByEmail(userEmail);

        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH);

        var userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(user.getCreatedAt().format(dateTimeFormatter))
                .build();
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
}
