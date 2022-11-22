package com.wda.bookstoreManager.open_api.controllers;

import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api("Books management")
public interface BooksControllerOpenApi {

    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success book creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or book already registered on system")
    })
    BooksResponseDTO createBook(BooksRequestDTO bookRequestDTO);

    @ApiOperation(value = "Book update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book by user successfully updated"),
            @ApiResponse(code = 404, message = "Book not found error"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value " +
                    "or book already registered on system")
    })
    BooksResponseDTO updateById(@PathVariable Integer bookId, @RequestBody BooksRequestDTO booksRequestDTO);

    @ApiOperation(value = "Book find by id and user operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success book found"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    BooksResponseDTO getById(@PathVariable Integer bookId);

    @ApiOperation(value = "List all books operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book list found")
    })
    Page<BooksResponseDTO> findAll(Pageable pageable);

    @ApiOperation(value = "Book delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success book exclusion"),
            @ApiResponse(code = 404, message = "Book with id not found in the System")
    })
    void deleteById(@PathVariable Integer bookId);
}
