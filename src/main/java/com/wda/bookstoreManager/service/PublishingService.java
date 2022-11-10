package com.wda.bookstoreManager.service;

import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PublishingService {

    void createPublishing (PublishingRequestDTO publishingRequestDTO);

    void updatePublishing(Integer id, PublishingResponseDTO publishingResponseDTO);

    void delete(Integer id);

    PublishingEntity verifyAndGet(Integer publishingId);

    Page<PublishingEntity> getAll(Pageable pageable);

    PublishingResponseDTO getById(Integer publishingId);

    //PublishingEntity findPublishingById(Integer id);

   // PublishingEntity verifyExist(Long publishingId);

    //  PublishingEntity verifyExist(Long id);
}
