package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.mapper.PublishingMapper;
import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
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
        PublishingEntity updated = getById(id);

        PublishingEntity publishingUpdate = publishingMapper.toModel(publishingResponseDTO);
        publishingUpdate.setId(updated.getId());
        publishingUpdate.setName(publishingUpdate.getName());
        publishingUpdate.setCity(publishingUpdate.getCity());

        publishingRepository.save(publishingUpdate);
    }

    public PublishingEntity getById(Integer id){
        return publishingRepository.findById(id);
    }

    public void delete(Integer id){
        PublishingEntity publishingDeleted = getById(id);
        if(!publishingDeleted.getBooks().isEmpty()){
            return;
        }
        publishingRepository.deleteById(id);
    }

    public Page<PublishingEntity> getAll(Pageable pageable){
        return publishingRepository.findAll(pageable);
    }

    public Optional<PublishingEntity> findById(Integer id){
        return Optional.ofNullable(publishingRepository.findById(id));
    }

    public PublishingEntity findPublishingId(Integer id){
        return publishingRepository.findById(id);
    }


  /*  public PublishingEntity verifyExist(Long id) {
        return publishingRepository.verifyExist(id);
    }*/
}
