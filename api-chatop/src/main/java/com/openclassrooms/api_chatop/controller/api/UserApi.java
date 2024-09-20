package com.openclassrooms.api_chatop.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.api_chatop.dto.UserDTO;
import static com.openclassrooms.api_chatop.utils.Constants.APP_ROOT;

public interface UserApi {

    @GetMapping(value = APP_ROOT + "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO> findAll();

    @PostMapping(value = APP_ROOT + "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDTO save(UserDTO userDTO);

    @DeleteMapping(value = APP_ROOT + "/users/{id}")
    void delete(@PathVariable Integer id);

}
