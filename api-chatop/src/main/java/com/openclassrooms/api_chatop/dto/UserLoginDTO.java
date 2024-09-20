package com.openclassrooms.api_chatop.dto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.openclassrooms.api_chatop.model.User;
import com.openclassrooms.api_chatop.services.UserService;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@Schema(description = "UserLoginDTO")
public class UserLoginDTO {
    @Schema(description = "User Password")
    private String password;
    @Schema(description = "User Email")
    private String email;

public boolean login(UserLoginDTO userLoginDTO, UserService userService) {
    User user = userService.findByEmail(userLoginDTO.getEmail());
    if (user != null) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return true;
        }
    }
    return false;
    }
}
