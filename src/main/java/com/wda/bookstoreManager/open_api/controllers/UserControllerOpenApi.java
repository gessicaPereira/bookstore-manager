package com.wda.bookstoreManager.open_api.controllers;

import com.wda.bookstoreManager.model.DTO.MessageDTO;
import com.wda.bookstoreManager.model.DTO.UserResponseDTO;
import com.wda.bookstoreManager.model.DTO.UsersRequestDTO;
import com.wda.bookstoreManager.model.UsersEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api("Users management")
public interface UserControllerOpenApi {

    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or user already registered on system ")
    })
    void createUser(UsersRequestDTO userToSaveDTO);

    @ApiOperation(value = "User delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success user exclusion"),
            @ApiResponse(code = 404, message = "User with id not found in the System")
    })
    void delete(Integer id);

    @ApiOperation(value = "User update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user update"),
            @ApiResponse(code = 404, message = "User with informed ID not found in the system")
    })
    void updateUser(@PathVariable Integer id, @RequestBody UserResponseDTO userToUpdateDTO);

    @ApiOperation(value = "Find user by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success user found"),
            @ApiResponse(code = 404, message = "User not found error code")
    })
    UserResponseDTO getById(@PathVariable Integer id);

    @ApiOperation(value = "List all registered users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered users")
    })
    ResponseEntity<Page<UsersEntity>> getAll(Pageable pageable);
}
