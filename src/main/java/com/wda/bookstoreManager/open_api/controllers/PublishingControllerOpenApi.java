package com.wda.bookstoreManager.open_api.controllers;


import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Api("Publishing management")
public interface PublishingControllerOpenApi {

    @ApiOperation(value = "Publishing creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success publishing creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or publishing already registered on system")
    })
    void createPublishing(@RequestBody PublishingRequestDTO publishingRequestDTO);

    @ApiOperation(value = "Publisher update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success publishing update"),
            @ApiResponse(code = 400, message = "Missing required field, or an error on validation field rules")
    })
    void updatePublishing(@PathVariable Integer id, @RequestBody PublishingResponseDTO publishingResponseDTO);

    @ApiOperation(value = "Delete publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success publishing deleted"),
            @ApiResponse(code = 404, message = "Publishing not found error")
    })
    void delete(@PathVariable Integer id);

    @ApiOperation(value = "List all registered publishing")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return all registered publishing")
    })
    ResponseEntity<Page<PublishingEntity>> getAll(Pageable pageable);

    @ApiOperation(value = "Find publisher by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success publishing found"),
            @ApiResponse(code = 404, message = "Publishing not found error")
    })
    PublishingResponseDTO getById(@PathVariable Integer publishingId);
}
