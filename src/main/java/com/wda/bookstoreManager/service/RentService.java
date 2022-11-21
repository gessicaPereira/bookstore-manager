package com.wda.bookstoreManager.service;

import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentService {

    Page<RentResponseDTO> findAll(Pageable pageable);

    RentResponseDTO getById(Integer id);

    RentResponseDTO create(RentRequestDTO rentRequestDTO);

    void verifyDeleteBook(Integer id);

    void devolution(Integer id);

    void delete(Integer id);
}
