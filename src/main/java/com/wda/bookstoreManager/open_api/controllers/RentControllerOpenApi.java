package com.wda.bookstoreManager.open_api.controllers;

import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api("Rent management")
public interface RentControllerOpenApi {

    @ApiOperation(value = "Rent creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success rent creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or rent already registered on system")
    })
    RentResponseDTO create(@RequestBody RentRequestDTO rentRequestDTO);

    @ApiOperation(value = "Rent devolution operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book by user successfully returned"),
            @ApiResponse(code = 404, message = "Book not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or rent already registered on system")
    })
    void devolution(@PathVariable Integer id);

    @ApiOperation(value = "Rent find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success rent found"),
            @ApiResponse(code = 404, message = "Rent not found error")
    })
    RentResponseDTO getById(@PathVariable Integer id);

    @ApiOperation(value = "List all books operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book list found")
    })
    Page<RentResponseDTO> findAll(Pageable pageable);

    @ApiOperation(value = "Rent delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success rent exclusion"),
            @ApiResponse(code = 404, message = "Rent with id not found in the System")
    })
    void delete(@PathVariable Integer id);
}
