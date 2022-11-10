package com.wda.bookstoreManager.controller;

import com.wda.bookstoreManager.mapper.PublishingMapper;
import com.wda.bookstoreManager.model.DTO.PublishingRequestDTO;
import com.wda.bookstoreManager.model.DTO.PublishingResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import com.wda.bookstoreManager.open_api.controllers.PublishingControllerOpenApi;
import com.wda.bookstoreManager.service.PublishingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/publishing")
public class PublishingController implements PublishingControllerOpenApi {


    @Autowired
    private PublishingService publishingService;

    @Autowired
    private PublishingMapper publishingMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createPublishing(@RequestBody PublishingRequestDTO publishingRequestDTO){
        publishingService.createPublishing(publishingRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePublishing(@PathVariable Integer id, @RequestBody PublishingResponseDTO publishingResponseDTO){
        publishingService.updatePublishing(id, publishingResponseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        publishingService.delete(id);
    }

    @GetMapping
    public ResponseEntity<Page<PublishingEntity>> getAll(Pageable pageable){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(publishingService.getAll(pageable));
    }

    @GetMapping("/{publishingId}")
    public PublishingResponseDTO getById(Integer publishingId) {
        return publishingService.getById(publishingId);
    }
}
