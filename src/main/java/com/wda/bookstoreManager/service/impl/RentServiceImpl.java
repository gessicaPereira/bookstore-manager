package com.wda.bookstoreManager.service.impl;


import com.wda.bookstoreManager.exception.*;
import com.wda.bookstoreManager.mapper.RentMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.model.enums.Status;
import com.wda.bookstoreManager.repository.RentRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.RentService;
import com.wda.bookstoreManager.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;

    private final BookService bookService;

    private final UserService userService;

    private RentMapper rentMapper;

    @Autowired
    @Lazy
    public RentServiceImpl(RentRepository rentRepository, BookService bookService, UserService userService, RentMapper rentMapper){
        this.rentRepository = rentRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.rentMapper = rentMapper;
    }

    public Page<RentResponseDTO> findAll(Pageable pageable){
        return rentRepository.findAll(pageable)
                .map(rentMapper::toDTO);
    }

    public RentResponseDTO getById(Integer rentId){
        return rentRepository.findById(rentId)
                .map(rentMapper::toDTO)
                .orElseThrow(() -> new RentNotFoundException(rentId));
    }

    public RentResponseDTO create(RentRequestDTO rentRequestDTO){

        BooksEntity foundBook = bookService.verifyAndGet(rentRequestDTO.getBookId());
        UsersEntity foundUser = userService.verifyAndGet(rentRequestDTO.getUser());

        RentEntity rentToSave = rentMapper.toRentModel(rentRequestDTO);
        rentToSave.setBooks(foundBook);
        rentToSave.setUsers(foundUser);
        rentToSave.setStatus(enumStatusValue(rentToSave.getReturn_date(), rentToSave.getForecast_return()));
        rentToSave.setId(null);
        foundBook.setQuantity(foundBook.getQuantity() -1);
        //rentToSave.setStatus(Status.PROGRESS);
        verifyCreate(foundUser, foundBook);
        //verifyQuantity(foundBook.getQuantity());

        RentEntity savedRent = rentRepository.save(rentToSave);
        return rentMapper.toDTO(savedRent);

    }


    public void devolution(Integer id){
        RentEntity foundRent = verifyAndGet(id);
        //verifyReturn(foundRent.getReturn_date(), foundRent.getForecast_return());

        //RentEntity rentToDevolution = rentMapper.toRentModel(id);
        //foundRent.setId(id);
        foundRent.setReturn_date(LocalDate.now());
        bookAddQuantity(foundRent.getReturn_date(), foundRent.getBooks());
        foundRent.setStatus(enumStatusValue(foundRent.getReturn_date(), foundRent.getForecast_return()));
        RentEntity savedRent = rentRepository.save(foundRent);
        rentMapper.toDTO(savedRent);
    }

    private void bookAddQuantity(LocalDate return_date, BooksEntity books){
        if (return_date != null){
            bookService.incrementQuantity(books);
        }
    }


    public void delete(Integer id) {
        BooksEntity books = verifyAndGet(id).getBooks();
        rentRepository.deleteById(id);
    }


    private Status enumStatusValue(LocalDate return_date, LocalDate forecast_return){
        if (return_date == null){
            return Status.PROGRESS;
        }else if (return_date.compareTo(forecast_return) > 0){
            return Status.LATE;
        }else if (return_date.compareTo(forecast_return) < 0){
            return Status.DEADLINE;
        } else {
            return Status.DEADLINE;
        }
    }

    private void verifyReturn(LocalDate rental_date, LocalDate forecast_return){
        if (forecast_return.compareTo(rental_date) < 0){
            throw new InvalidDateReturnException("");
        }
    }

    private void verifyCreate(UsersEntity users, BooksEntity books){
        Optional<RentEntity> foundRent = rentRepository.findByBooksAndUsersAndStatus(books, users, Status.PROGRESS);
        if (foundRent.isPresent()){
            throw new RentImpossibleException("Alguguel já registrado");
        }
    }

    private RentEntity verifyAndGet(Integer id){
        return rentRepository.findById(id)
                .orElseThrow(() -> new RentNotFoundException(id));
    }

    public void verifyDeleteBook(Integer id){
        BooksEntity books = bookService.verifyAndGet(id);
        List<RentEntity> rentEntities = rentRepository.findByBooks(books);
        if(rentEntities.size() > 0){
            throw new RentImpossibleException("book associated with rent");
        }
    }

    private void verifyRentDelete(Integer id){
        Status status = rentRepository.findById(id).get().getStatus();
        if (status.equals(Status.PROGRESS)){
            throw new DeleteNotPossibleException("Rent in progress!");
        }
    }

    private void verifyQuantity(Integer quantity){
        if (quantity <= 0){
            throw new InvalidQuantityException();
        }
    }

}
