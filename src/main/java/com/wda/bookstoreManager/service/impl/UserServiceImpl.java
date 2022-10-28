package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.mapper.UserMapper;
import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.DTO.UsersRequestDTO;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.repository.UsersRepository;
import com.wda.bookstoreManager.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final UserMapper userMapper;


    public UserServiceImpl(@Lazy UsersRepository usersRepository, UserMapper userMapper){
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }


    @Override
    public void createUser(UsersRequestDTO usersRequestDTO){
        UsersEntity userToCreate = userMapper.toUserModel(usersRequestDTO);
        usersRepository.save(userToCreate);
    }


    @Override
    public void updateUser(Integer id, UserResponseDTO userResponseDTO){
        UsersEntity updated = getById(id);

        UsersEntity userUpdate = userMapper.toModel(userResponseDTO);
        userUpdate.setId(updated.getId());
        userUpdate.setName(userUpdate.getName());
        userUpdate.setCity(userUpdate.getCity());
        userUpdate.setAddress(userUpdate.getAddress());
        userUpdate.setEmail(userUpdate.getEmail());

        usersRepository.save(userUpdate);
    }

    public UsersEntity getById(Integer id){
        return usersRepository.findById(id);
    }

    public void delete (Integer id){
        UsersEntity userDeleted = getById(id);
        if(!userDeleted.getRentEntities().isEmpty()){
            return;
        }
        usersRepository.deleteById(id);
    }

    public Page<UsersEntity> getAll(Pageable pageable){
        return usersRepository.findAll(pageable);
    }

  /*  public UsersEntity findById(Integer id){
        return usersRepository.findById(id);
    }*/


}
