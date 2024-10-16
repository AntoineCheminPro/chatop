package com.openclassrooms.api_chatop.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.api_chatop.exceptions.UserNotFoundException;
import com.openclassrooms.api_chatop.models.User;
import com.openclassrooms.api_chatop.repositories.UserRepository;

import lombok.Data;

@Data
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer id) {
        var user =  userRepository.findById(id);
        return user.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User getUserByEmail(String email) {
        var user =  userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
