package com.openclassrooms.api_chatop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.api_chatop.controller.api.UserApi;
import com.openclassrooms.api_chatop.dto.UserDTO;
import com.openclassrooms.api_chatop.services.UserService;

@RestController
public class UserController implements UserApi {

    // field injection
    @Autowired
    private final UserService userService;

    //Getter injection
    public UserService getUserService() {
        return userService;
    }

    // constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO findById(Integer id) {
        return userService.findById(id);
    }

    @Override
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @Override
    public void delete(Integer id) {
        userService.delete(id);
    }
}
