package com.wda.bookstoreManager.mapper;

import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublishingMapper {


    private final ModelMapper mapper;

    public PublishingEntity toPublishingModel(PublishingRequestDTO publishingRequestDTO){
        return mapper.map(publishingRequestDTO, PublishingEntity.class);
    }

    public PublishingEntity toModel(PublishingResponseDTO publishingResponseDTO){
        return mapper.map(publishingResponseDTO, PublishingEntity.class);
    }

    public PublishingResponseDTO toDTO(PublishingEntity publishingEntity){
        return mapper.map(publishingEntity, PublishingResponseDTO.class);
    }

}
