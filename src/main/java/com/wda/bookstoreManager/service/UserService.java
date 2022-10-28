package com.wda.bookstoreManager.service;

import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.DTO.UsersRequestDTO;
import com.wda.bookstoreManager.model.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    void createUser(UsersRequestDTO usersRequestDTO);

    void updateUser(Integer id, UserResponseDTO userResponseDTO);

    void delete (Integer id);

    UsersEntity getById (Integer id);

    Page<UsersEntity> getAll(Pageable pageable);

   // UsersEntity findById(Integer id);

}
