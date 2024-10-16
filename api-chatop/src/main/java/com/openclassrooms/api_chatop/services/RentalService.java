package com.openclassrooms.api_chatop.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.openclassrooms.api_chatop.exceptions.RentalNotFoundException;
import com.openclassrooms.api_chatop.models.Rental;
import com.openclassrooms.api_chatop.models.responses.RentalResponse;
import com.openclassrooms.api_chatop.repositories.RentalRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@Data
@Service
public class RentalService {
    @Value("${server.host}")  //127.0.0.1
    private String serverHost;
    @Value("${server.port}") //3001
    private String serverPort;
    private final String imagesDirectoryPathname = "images";
    @Value("${upload.volume}")
    private String UPLOAD_VOLUME_PATH;
    @Value("${server.servlet.context-path}") // /api
    private String contextPath;

    private final RentalRepository rentalRepository;
    private String imageServerUrl;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @PostConstruct
    public void init() {
        imageServerUrl = "http://" + serverHost + ":" + serverPort + contextPath + "/" + imagesDirectoryPathname + "/";
    }

    public Rental getRental(Integer id) {
        var rental = rentalRepository.findById(id);
        return rental.orElseThrow(() -> new RentalNotFoundException("Rental not found"));
    }

    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public Rental saveOrUpdateRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public RentalResponse buildRentalResponse(Rental rental){
        return RentalResponse.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(imageServerUrl + rental.getPicture())
                .description(rental.getDescription())
                .owner_id(rental.getOwner().getId())
                .createdAt(rental.getCreatedAt())
                .updatedAt(rental.getUpdatedAt())
                .build();
    }

    public String saveFile(MultipartFile imageFile) throws IOException {
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        Path uploadPath = Path.of(UPLOAD_VOLUME_PATH);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }
}
