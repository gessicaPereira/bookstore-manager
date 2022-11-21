package com.wda.bookstoreManager.controller;

import com.wda.bookstoreManager.mapper.RentMapper;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.repository.RentRepository;
import com.wda.bookstoreManager.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rents")
public class RentController {

    private RentService rentService;

    private RentRepository rentRepository;

    private RentMapper rentMapper;

    @Autowired
    public RentController(RentService rentService, RentRepository rentRepository, RentMapper rentMapper){
        this.rentService = rentService;
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentResponseDTO create(@RequestBody RentRequestDTO rentRequestDTO) {
        return rentService.create(rentRequestDTO);
    }

    @GetMapping
    public Page<RentResponseDTO> findAll(Pageable pageable) {
        return rentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public RentResponseDTO getById(@PathVariable Integer id) {
        return rentService.getById(id);
    }

    @PutMapping("/devolution/{id}")
    public void devolution(@PathVariable Integer id) {
             rentService.devolution(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        rentService.delete(id);
    }
}
