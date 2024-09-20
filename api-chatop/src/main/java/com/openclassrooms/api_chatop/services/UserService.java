package com.openclassrooms.api_chatop.services;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.time.Instant;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.openclassrooms.api_chatop.Exception.EntityNotFoundException;
import com.openclassrooms.api_chatop.Exception.ErrorCodes;
import com.openclassrooms.api_chatop.Exception.InvalidEntityException;
import com.openclassrooms.api_chatop.dto.UserDTO;
import com.openclassrooms.api_chatop.dto.UserRegistrationDTO;
import com.openclassrooms.api_chatop.model.User;
import com.openclassrooms.api_chatop.repository.UserRepository;
import com.openclassrooms.api_chatop.security.JwtTokenUtil;
import com.openclassrooms.api_chatop.validator.UserValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;
    @Autowired
    private final PasswordEncoder passwordEncoder;

public UserService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, PasswordEncoder passwordEncoder) { 
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO save(UserDTO userDTO){
        List<String> errors = UserValidator.validate(userDTO);
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.USER_NOT_VALID, errors);
        }

        User user = userRepository.save(userDTO.toEntity(userDTO));
        log.info("Enregistrement de l'utilisateur {}", userDTO);
        return UserDTO.fromEntity(user);
    };

    public String register(UserRegistrationDTO userRegistrationDTO){
       User user = new User();
        user.setName(userRegistrationDTO.getName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setCreated_at(Instant.now());
        user.setUpdated_at(Instant.now());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        return token; 
    }



    public UserDTO findById(Integer id){
        if(id == null){
            log.error("L'id de l'utilisateur est null");
            return null;
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return UserDTO.fromEntity(user.get());
        }
        throw new EntityNotFoundException("L'utilisateur avec l'id " + id + " n'existe pas", ErrorCodes.USER_NOT_FOUND);
    };

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> 
        new EntityNotFoundException("L'utilisateur avec l'email " + email + " n'existe pas", ErrorCodes.USER_NOT_FOUND));
}
    public List<UserDTO> findAll(){
        return userRepository.findAll().stream()
            .map(UserDTO::fromEntity)
            .collect(Collectors.toList());
    };

    public void delete(Integer id){
        if(id == null){
            log.error("L'id de l'utilisateur est null");
            return;
        }
        userRepository.deleteById(id);
        log.info("L'utilisateur avec l'id " + id + " a été supprimé");
    };
}
