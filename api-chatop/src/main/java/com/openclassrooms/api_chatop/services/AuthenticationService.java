package com.openclassrooms.api_chatop.services;

import com.openclassrooms.api_chatop.models.requests.LoginUserRequest;
import com.openclassrooms.api_chatop.models.requests.RegisterUserRequest;
import com.openclassrooms.api_chatop.models.User;
import com.openclassrooms.api_chatop.models.responses.AuthenticationResponse;
import com.openclassrooms.api_chatop.repositories.RoleRepository;
import com.openclassrooms.api_chatop.repositories.UserRepository;
import com.openclassrooms.api_chatop.security.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registerUser(RegisterUserRequest registerUserRequest) {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role user was not found or not initialized"));
        var user = User.builder()
                .name(registerUserRequest.getName())
                .email(registerUserRequest.getEmail())
                .password(passwordEncoder.encode(registerUserRequest.getPassword()))
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        return getToken(user);
    }

    public AuthenticationResponse loginUser(LoginUserRequest loginUserRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserRequest.getEmail(),
                        loginUserRequest.getPassword()
                )
        );
        return getToken((User)authentication.getPrincipal());
    }

    public AuthenticationResponse getToken(User user) {
        var claims = new HashMap<String, Object>();

        claims.put("fullName", user.getName());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
