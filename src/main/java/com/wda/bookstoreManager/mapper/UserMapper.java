package com.wda.bookstoreManager.mapper;

import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.DTO.UsersRequestDTO;
import com.wda.bookstoreManager.model.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

    public UsersEntity toUserModel(UsersRequestDTO usersRequestDTO){
        return mapper.map(usersRequestDTO, UsersEntity.class);
    }

    public UsersEntity toModel(UserResponseDTO userResponseDTO){
        return mapper.map(userResponseDTO, UsersEntity.class);
    }
    public UserResponseDTO toUserDTO(UsersEntity usersEntity){
        return mapper.map(usersEntity, UserResponseDTO.class);
    }


}
