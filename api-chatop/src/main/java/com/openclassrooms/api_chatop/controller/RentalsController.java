package com.openclassrooms.api_chatop.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.api_chatop.controller.api.RentalsApi;
import com.openclassrooms.api_chatop.dto.RentalsDTO;
import com.openclassrooms.api_chatop.services.ImageService;
import com.openclassrooms.api_chatop.services.RentalsService;

@RestController
public class RentalsController implements RentalsApi {
    // field injection
    @Autowired
    private final RentalsService rentalsService;
    private final ImageService imageService;

    //Getter injection
    public RentalsService getRentalsService() {
        return rentalsService;
    }

    // constructor injection
    public RentalsController(RentalsService rentalsService, ImageService imageService) {
    this.rentalsService = rentalsService;
    this.imageService = imageService;
    }

    @Override
    public RentalsDTO findById(Integer id) {
        return rentalsService.findById(id);
    }

    @Override
    public List<RentalsDTO> findAll() {
        return rentalsService.findAll();
    }

    @Override
    public RentalsDTO save(RentalsDTO rentalsDTO) {
        return rentalsService.save(rentalsDTO);
    }

    @Override
    public void delete(Integer id) {
        rentalsService.delete(id);
    }

    @Override
    public ResponseEntity<String> uploadImage(MultipartFile file) {
        String imageUrl = imageService.uploadImage(file);
        return ResponseEntity.ok(imageUrl);
    }
}
