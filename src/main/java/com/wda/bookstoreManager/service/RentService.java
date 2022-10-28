package com.wda.bookstoreManager.service;

import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RentService {

    RentResponseDTO createRent(RentRequestDTO rentRequestDTO);

    void delete(RentEntity rent);

    Page<RentResponseDTO> findAll(Pageable pageable);

    Optional<RentResponseDTO> getById(Integer id);

}
