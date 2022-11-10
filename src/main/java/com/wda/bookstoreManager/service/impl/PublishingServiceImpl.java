package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.exception.BookNotFoundException;
import com.wda.bookstoreManager.exception.DeleteDeniedException;
import com.wda.bookstoreManager.exception.DeletePublishingDeniedException;
import com.wda.bookstoreManager.exception.PublishingNotFoundException;
import com.wda.bookstoreManager.mapper.PublishingMapper;
import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.repository.PublishingRepository;
import com.wda.bookstoreManager.service.PublishingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PublishingServiceImpl implements PublishingService {

    private final PublishingRepository publishingRepository;

    private final PublishingMapper publishingMapper;


    public PublishingServiceImpl(PublishingRepository publishingRepository, PublishingMapper publishingMapper){
        this.publishingRepository = publishingRepository;
        this.publishingMapper = publishingMapper;
    }

    @Override
    public void createPublishing(PublishingRequestDTO publishingRequestDTO){
        PublishingEntity publishingToCreate = publishingMapper.toPublishingModel(publishingRequestDTO);
        publishingRepository.save(publishingToCreate);
    }

    @Override
    public void updatePublishing(Integer id, PublishingResponseDTO publishingResponseDTO){
        PublishingEntity updated = verifyAndGet(id);

        PublishingEntity publishingUpdate = publishingMapper.toModel(publishingResponseDTO);
        publishingUpdate.setId(updated.getId());
        publishingUpdate.setName(publishingUpdate.getName());
        publishingUpdate.setCity(publishingUpdate.getCity());

        publishingRepository.save(publishingUpdate);
    }

    public PublishingEntity verifyAndGet(Integer publishingId){
        return publishingRepository.findPublishingById(publishingId);
    }

    public void delete(Integer publishingId){
        PublishingEntity publishingDeleted = publishingRepository.findPublishingById(publishingId);
        if(!publishingDeleted.getBooks().isEmpty()){
            throw new DeletePublishingDeniedException();
        }
        publishingRepository.deleteById(publishingDeleted.getId());
    }

    public Page<PublishingEntity> getAll(Pageable pageable){
        return publishingRepository.findAll(pageable);
    }

    public PublishingResponseDTO getById(Integer publishingId){
        return publishingRepository.findById(publishingId)
                .map(publishingMapper::toDTO)
                .orElseThrow(() -> new PublishingNotFoundException(publishingId));
    }

    /*public PublishingEntity findPublishingId(Integer id){
        return publishingRepository.findById(id);
    }*/


  /*  public PublishingEntity verifyExist(Long id) {
        return publishingRepository.verifyExist(id);
    }*/
}
