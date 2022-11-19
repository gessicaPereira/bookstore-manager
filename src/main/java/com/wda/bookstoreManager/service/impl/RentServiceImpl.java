package com.wda.bookstoreManager.service.impl;


import com.wda.bookstoreManager.exception.InvalidDateReturnException;
import com.wda.bookstoreManager.exception.RentAlreadyExistException;
import com.wda.bookstoreManager.exception.RentImpossibleException;
import com.wda.bookstoreManager.exception.RentNotFoundException;
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


        RentEntity renToSave = rentMapper.toRentModel(rentRequestDTO);
        renToSave.setBooks(foundBook);
        renToSave.setUsers(foundUser);
        renToSave.setStatus(enumStatusValue(renToSave.getReturn_date(), renToSave.getForecast_return()));
        verifyCreate(foundUser, foundBook); // se der erro, passar pra linha de cima

        BooksEntity alterQuantity = renToSave.getBooks();
        verifyReturn(renToSave.getRental_date(), renToSave.getForecast_return());
        //verifyBookQuantity(alterQuantity, false);

        RentEntity savedRent = rentRepository.save(renToSave);
        return rentMapper.toDTO(savedRent);

    }

    public RentResponseDTO devolution(Integer rentId){
        RentEntity foundRent = verifyAndGet(rentId);

        RentEntity rentToSave = foundRent;
        foundRent.setReturn_date(LocalDate.now());
        //verifyBookQuantity(rentToSave.getBooks(), true);

        RentEntity savedRent = rentRepository.save(rentToSave);
        return rentMapper.toDTO(savedRent);

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
            throw new RentImpossibleException("Not possible");
        }
    }

    /*private void verifyBookQuantity(BooksEntity books, boolean addQuantity){
        if (addQuantity){
            BooksEntity alterQuantity = books;
            alterQuantity.setQuantity(alterQuantity.getQuantity() + 1);
            alterQuantity.setQuantityRented(alterQuantity.getQuantityRented() - 1);
        } else {
            books.setQuantity(books.getQuantity() - 1);
            books.setQuantityRented(books.getQuantityRented() + 1);
        }
    }*/

    /*private void verifyReturnExist(RentEntity foundRent){
        if (!foundRent.getReturn_date().equals(Status.PROGRESS)){
            throw new RentImpossibleException("Book returned!");
        }
    }*/

    private RentEntity verifyAndGet(Integer rentId){
        return rentRepository.findById(rentId)
                .orElseThrow(() -> new RentNotFoundException(rentId));
    }

    public void verifyDeleteBook(Integer id){
        BooksEntity books = bookService.verifyAndGet(id);
        List<RentEntity> rentEntities = rentRepository.findByBooks(books);
        if(rentEntities.size() > 0){
            throw new RentImpossibleException("book associated with rent");
        }
    }

}
