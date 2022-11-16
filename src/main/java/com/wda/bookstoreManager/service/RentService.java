package com.wda.bookstoreManager.service;

import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RentService {

    void createRent(RentRequestDTO rentRequestDTO);

    Page<RentResponseDTO> getAll(Pageable pageable);

    RentResponseDTO getById(Integer id);

    RentResponseDTO update(Integer id, RentRequestDTO rentRequestDTO);

    void delete(Integer id);

    void devolution(Integer id);


}
