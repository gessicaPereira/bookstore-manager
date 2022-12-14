package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.exception.*;
import com.wda.bookstoreManager.mapper.BooksMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import com.wda.bookstoreManager.repository.BooksRepository;
import com.wda.bookstoreManager.repository.PublishingRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.PublishingService;
import com.wda.bookstoreManager.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class BookServiceImpl implements BookService {
    private final BooksRepository booksRepository;

    private final PublishingRepository publishingRepository;
    private final PublishingService publishingService;

    private final RentService rentService;
    private final BooksMapper booksMapper;


    @Autowired
    @Lazy
    public BookServiceImpl(BooksRepository booksRepository, PublishingService publishingService, BooksMapper booksMapper, PublishingRepository publishingRepository, RentService rentService){
        this.booksRepository = booksRepository;
        this.publishingRepository = publishingRepository;
        this.publishingService = publishingService;
        this.rentService = rentService;
        this.booksMapper = booksMapper;
    }

    public BooksResponseDTO createBook(BooksRequestDTO booksRequestDTO){
        verifyBook(booksRequestDTO.getName());
        PublishingEntity foundPublishing = publishingService.verifyAndGet(booksRequestDTO.getPublishingId());

        BooksEntity bookToSave = booksMapper.toBookModel(booksRequestDTO);
        bookToSave.setPublishing(foundPublishing);
        bookToSave.setId(null);
        BooksEntity savedBook = booksRepository.save(bookToSave);

        return booksMapper.toBookDTO(savedBook);

    }

    public BooksResponseDTO getById(Integer bookId){
        return booksRepository.findById(bookId)
                .map(booksMapper::toBookDTO)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public Page<BooksResponseDTO> findAll(Pageable pageable){
        return booksRepository.findAll(pageable)
                .map(booksMapper::toBookDTO);
    }

    /*public Page<BooksEntity> getAll(Pageable pageable){
        return booksRepository.findAll(pageable);
    }*/

    /*public void verifyPublishing(Integer publishingId){
        if (publishingRepository.findById(publishingId).isEmpty()){
            throw new PublishingNotFoundException(publishingId);
        }
    }*/

    public void verifyBook(String name){
        Optional<BooksEntity> duplicated = booksRepository.findByName(name);
        if (duplicated.isPresent()){
            throw new BookAlreadyExistException(name);
        }
    }


    public void deleteById(Integer bookId){
      rentService.verifyDeleteBook(bookId);
        booksRepository.deleteById(bookId);
    }

    public BooksResponseDTO updateById(Integer bookId, BooksRequestDTO booksRequestDTO){
        BooksEntity foundBook  = verifyAndGet(bookId);
        PublishingEntity foundPublishing = publishingService.verifyAndGet(booksRequestDTO.getPublishingId());

        BooksEntity bookToUpdate = booksMapper.toBookModel(booksRequestDTO);
        bookToUpdate.setId(bookId);
        bookToUpdate.setPublishing(foundPublishing);
        bookToUpdate.setName(bookToUpdate.getName());
        bookToUpdate.setAuthor(bookToUpdate.getAuthor());
        bookToUpdate.setQuantityRented(foundBook.getQuantityRented());
        bookToUpdate.setLaunch(booksRequestDTO.getLaunch());
        BooksEntity updatedBook = booksRepository.save(bookToUpdate);
        return booksMapper.toBookDTO(updatedBook);

    }

    public BooksEntity verifyAndGet(Integer bookId){
        return booksRepository.findById(bookId)
                .orElseThrow(()-> new BookNotFoundException(bookId));
    }

    public void incrementQuantity(BooksEntity books){
        books.setQuantity(books.getQuantity() + 1);
        booksRepository.save(books);
    }

}
