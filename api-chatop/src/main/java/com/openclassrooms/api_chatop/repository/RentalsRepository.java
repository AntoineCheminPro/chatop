package com.openclassrooms.api_chatop.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api_chatop.model.Rentals;

@Repository

public interface RentalsRepository extends JpaRepository<Rentals, Integer> {

}
