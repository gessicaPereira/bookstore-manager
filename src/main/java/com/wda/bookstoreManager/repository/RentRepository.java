package com.wda.bookstoreManager.repository;

import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RentRepository extends JpaRepository<RentEntity, Integer> {

    //Optional <RentResponseDTO> rentById (Integer id);
}
