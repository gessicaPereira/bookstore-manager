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

    // RentResponseDTO update(Integer id, RentRequestDTO rentRequestDTO);

    void devolution(Integer id);

    void delete(Integer id);


/*
    RentResponseDTO create(RentRequestDTO rentRequestDTO);

    Page<RentResponseDTO> findAll(Pageable pageable);

    RentResponseDTO findById(Integer id);

    //RentResponseDTO update(Integer id, RentRequestDTO rentRequestDTO);

    void delete(Integer id);

    RentResponseDTO devolution(Integer id);
*/
    void verifyDeleteBook(Integer id);


}
