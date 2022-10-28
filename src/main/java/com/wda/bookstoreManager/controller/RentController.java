package com.wda.bookstoreManager.controller;

import com.wda.bookstoreManager.mapper.RentMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import com.wda.bookstoreManager.repository.RentRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rents")
public class RentController {

    private BookService bookService;
    private RentRepository rentRepository;
    private RentMapper rentMapper;
    private RentService rentService;

    @Autowired
    public RentController(BookService bookService, RentRepository rentRepository, RentMapper rentMapper, RentService rentService) {
        this.bookService = bookService;
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
        this.rentService = rentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentResponseDTO createRent(@RequestBody RentRequestDTO rentRequestDTO) {
        return rentService.createRent(rentRequestDTO);
    }

    @DeleteMapping("/rent")
    public void delete(@PathVariable RentEntity rent) {
        rentService.delete(rent);
    }

    @GetMapping
    public Page<RentResponseDTO> findAll(Pageable pageable) {
        return rentService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Optional<RentResponseDTO> getById(@PathVariable Integer id) {
        return rentService.getById(id);
    }

/* @GetMapping
    public Page<RentResponseDTO> findAll(Pageable pageable) {
        return rentService.findAll(pageable);
    }

    @GetMapping("/{Id}")
    public RentEntity findById(@PathVariable Integer id) {
        return rentService.findById(id);
    }
*/
}
