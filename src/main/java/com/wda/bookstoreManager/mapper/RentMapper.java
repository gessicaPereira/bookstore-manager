package com.wda.bookstoreManager.mapper;

import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentMapper {

    private final ModelMapper mapper;

    public RentEntity toRentModel(RentRequestDTO rentRequestDTO){
        return mapper.map(rentRequestDTO, RentEntity.class);
    }

    public RentEntity toModel(RentResponseDTO rentResponseDTO){
        return mapper.map(rentResponseDTO, RentEntity.class);
    }

    public RentResponseDTO toDTO(RentEntity rentEntity){
        return mapper.map(rentEntity, RentResponseDTO.class);
    }

}
