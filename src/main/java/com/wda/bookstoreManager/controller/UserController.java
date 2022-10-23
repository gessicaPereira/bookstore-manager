package com.wda.bookstoreManager.controller;

import com.wda.bookstoreManager.mapper.UserMapper;
import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.DTO.UsersRequestDTO;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UsersRequestDTO usersRequestDTO){
        userService.createUser(usersRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable Integer id, @RequestBody UserResponseDTO userResponseDTO){
        userService.updateUser(id, userResponseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){
        userService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<UsersEntity>> getAll(Pageable pageable){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity <Object> findById(@PathVariable Integer id){
        Optional<UsersEntity> usersEntityOptional = Optional.ofNullable(userService.getById(id));
        return usersEntityOptional.<ResponseEntity<Object>>map(usersEntity -> ResponseEntity.status(HttpStatus.OK).body(usersEntity)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }

}
