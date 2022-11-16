package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.exception.*;
import com.wda.bookstoreManager.mapper.RentMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.RentRequestDTO;
import com.wda.bookstoreManager.model.DTO.RentResponseDTO;
import com.wda.bookstoreManager.model.RentEntity;
import com.wda.bookstoreManager.model.UsersEntity;
import com.wda.bookstoreManager.model.enums.Status;
import com.wda.bookstoreManager.repository.BooksRepository;
import com.wda.bookstoreManager.repository.RentRepository;
import com.wda.bookstoreManager.repository.UsersRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.RentService;
import com.wda.bookstoreManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    private final BooksRepository booksRepository;

    private final UsersRepository usersRepository;

    private final RentMapper rentMapper;

    private final BookService bookService;

    private final UserService userService;


    @Autowired
    public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper, BookService bookService, UserService userService, BooksRepository booksRepository, UsersRepository usersRepository) {
        this.rentRepository = rentRepository;
        this.booksRepository = booksRepository;
        this.usersRepository = usersRepository;
        this.rentMapper = rentMapper;
        this.bookService = bookService;
        this.userService = userService;
    }

    public void createRent(RentRequestDTO rentRequestDTO){
        BooksEntity foundBook = bookService.verifyAndGet(rentRequestDTO.getBookId());
        UsersEntity foundUser = userService.verifyAndGet(rentRequestDTO.getUser());

        RentEntity rent = rentMapper.toRentModel(rentRequestDTO);
        //foundBook.setQuantityRented(foundBook.getQuantityRented() +1);
        rent.setBooks(foundBook);
        rent.setUsers(foundUser);
        foundBook.setQuantity(foundBook.getQuantity() -1);
        //booksRepository.save(foundBook);
        rent.setStatus(Status.PROGRESS);
        rentRepository.save(rent);

    }

    public Page<RentResponseDTO> getAll(Pageable pageable){
        return rentRepository.findAll(pageable).map(rentMapper::toDTO);
    }

    public RentResponseDTO getById(Integer id){
        return rentRepository.findById(id).map(rentMapper::toDTO)
                .orElseThrow(() -> new RentImpossibleException("Id not found"));
    }

    public RentResponseDTO update(Integer id, RentRequestDTO rentRequestDTO){
        Optional<BooksEntity> updatedBook = booksRepository.findById(rentRequestDTO.getBookId());
        Optional<UsersEntity> updatedUser = usersRepository.findById(rentRequestDTO.getUser());
        Optional<RentEntity> updatedRent = rentRepository.findById(id);

        if (updatedBook.isPresent()){
            if (updatedUser.isPresent()){
                if (updatedRent.get().getStatus().equals(Status.PROGRESS)){
                    throw new RentImpossibleException("This rental has already been made and the book has not been delivered");
                }
            }
        }
        if (updatedRent.isEmpty()){
            throw new RentNotFoundException(id);
        }
        RentEntity rent = rentMapper.toRentModel(rentRequestDTO);
        rent.setId(id);
        rentRepository.save(rent);

        return rentMapper.toDTO(rent);
    }

    public void devolution(Integer id) {
        RentEntity rentDevolution = rentRepository.getReferenceById(id);
        Optional<RentEntity> rent = rentRepository.findById(id);
        Integer quantity = rentDevolution.getBooks().getQuantityRented();

        if (rent.isEmpty()) {
            throw new RentNotFoundException(id);
        }
        if (rent.get().getStatus().equals(Status.LATE)) {
            throw new RentStatusException("this book has already been delivered late!");
        }
        if (rent.get().getStatus().equals(Status.DEADLINE)) {
            throw new RentStatusException("this book has already been delivered!");
        }
        if (rent.get().getForecast_return().isBefore(LocalDate.now())) {
            rentDevolution.setStatus(Status.LATE);
        }
        else {
            rentDevolution.setStatus(Status.DEADLINE);
        }
        rentDevolution.setReturn_date(LocalDate.now());
        rentDevolution.getBooks().setQuantityRented(quantity +1);

        rentRepository.save(rentDevolution);
    }

    public void delete(Integer id){
        RentEntity rent = rentRepository.getReferenceById(id);
        Integer quantity = rent.getBooks().getQuantityRented();
        verifyDelete(id);
        rentRepository.deleteById(id);
        rent.getBooks().setQuantityRented(quantity -1);
    }

    public void verifyCreate(RentRequestDTO requestDTO){
        verifyCreateBook(requestDTO.getBookId());
        verifyCreateUser(requestDTO.getUser());
        verifyReturn(requestDTO.getRental_date(), requestDTO.getForecast_return());
    }

    private void verifyCreateBook(Integer id){
        if(booksRepository.findById(id).isEmpty()){
            throw new BookNotFoundException(id);
        }
    }

    public void verifyRent(BooksEntity books){
        verifyQuantity(books.getQuantity());
    }

    private void verifyCreateUser(Integer id){
        if (usersRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(id);
        }
    }

    private void verifyReturn(LocalDate rental_date, LocalDate forecast_return){
        if(rental_date.isAfter(forecast_return)){
            throw new InvalidDateReturnException("The rental date is not possible after the return forecast!");
        }
        if (rental_date.isAfter(LocalDate.now())){
            throw new RentImpossibleException("Rent must be made on the present date!");
        }
    }

    private void verifyDelete(Integer id){
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

