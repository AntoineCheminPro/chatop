package com.openclassrooms.api_chatop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api_chatop.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

}
