package com.openclassrooms.api_chatop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api_chatop.models.Rental;

@Repository

public interface RentalRepository extends JpaRepository<Rental, Integer> {

}
