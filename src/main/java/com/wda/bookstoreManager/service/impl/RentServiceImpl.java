package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.exception.*;
import com.wda.bookstoreManager.mapper.RentMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import com.wda.bookstoreManager.model.enums.Status;
import com.wda.bookstoreManager.repository.BooksRepository;
import com.wda.bookstoreManager.repository.RentRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.RentService;
import com.wda.bookstoreManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    private final BooksRepository booksRepository;

    private final RentMapper rentMapper;

    private final BookService bookService;

    private final UserService userService;


    @Autowired
    public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper, BookService bookService, UserService userService, BooksRepository booksRepository) {
        this.rentRepository = rentRepository;
        this.booksRepository = booksRepository;
        this.rentMapper = rentMapper;
        this.bookService = bookService;
        this.userService = userService;
    }

    public RentResponseDTO createRent(RentRequestDTO rentRequestDTO) {
        BooksEntity foundBook = booksRepository.findBookById(rentRequestDTO.getBookId());
        rentRequestDTO.setReturn_date(LocalDate.now(ZoneId.of("GMT-3")));
        rentRequestDTO.setRental_date(LocalDate.now(ZoneId.of("GMT-3")));

        if (rentRequestDTO.getForecast_return().isBefore(rentRequestDTO.getRental_date())) {
            throw new RentImpossibleException("não pode");
        } else {
            if (foundBook.getQuantity() > 0) {
                RentEntity rentToSave = rentMapper.toRentModel(rentRequestDTO);
                rentToSave.setStatus(Status.PROGRESS);
                rentToSave.setBooks(foundBook);
                foundBook.setQuantity(foundBook.getQuantity() - 1);
                //booksRepository.save(foundBook);

                RentEntity savedRent = rentRepository.save(rentToSave);
                return rentMapper.toDTO(savedRent);
            } else {
                throw new RuntimeException("to aqui");
            }
        }
    }


    public void delete(RentEntity rent) {
        BooksEntity books = booksRepository.findBookById(rent.getBooks().getId());

        if (rent.getReturn_date() != null){
            throw new RentUpdateNotPossible("AQUI MUDAR");
        } else {
            books.setQuantity(books.getQuantity() +1);
            books.setQuantityRented(books.getQuantityRented() - 1);
            rentRepository.delete(rent);
        }
    }


    public Page<RentResponseDTO> findAll(Pageable pageable){
        return rentRepository.findAll(pageable)
                .map(rentMapper::toDTO);
    }

    public Optional<RentResponseDTO> getById(Integer id){
        return rentRepository.findById(id)
                .map(rentMapper::toDTO);
    }


}

