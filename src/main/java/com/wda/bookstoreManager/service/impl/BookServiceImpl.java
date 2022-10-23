package com.wda.bookstoreManager.service.impl;

import com.wda.bookstoreManager.exception.BookAlreadyExistException;
import com.wda.bookstoreManager.exception.BookNotFoundException;
import com.wda.bookstoreManager.mapper.BooksMapper;
import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import com.wda.bookstoreManager.model.PublishingEntity;
import com.wda.bookstoreManager.repository.BooksRepository;
import com.wda.bookstoreManager.service.BookService;
import com.wda.bookstoreManager.service.PublishingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BooksRepository booksRepository;
    private final PublishingService publishingService;
  //  private final RentRepository rentRepository;
    private final BooksMapper booksMapper;


    public BookServiceImpl(BooksRepository booksRepository, PublishingService publishingService, BooksMapper booksMapper){
        this.booksRepository = booksRepository;
        this.publishingService = publishingService;
      //  this.rentRepository = rentRepository;
        this.booksMapper = booksMapper;
    }

    public BooksResponseDTO createBook(BooksRequestDTO booksRequestDTO){
        verifyIfBookIsAlreadyRegistered(booksRequestDTO);
        PublishingEntity foundPublishing = publishingService.findPublishingId(booksRequestDTO.getPublishingId());

        if (booksRequestDTO.getQuantity() < 1){
            return null;
        }

        BooksEntity bookToSave = booksMapper.toBookModel(booksRequestDTO);
        bookToSave.setName(bookToSave.getName());
        bookToSave.setAuthor(bookToSave.getAuthor());
        bookToSave.setPublishing(foundPublishing);
        BooksEntity savedBook = booksRepository.save(bookToSave);

        return booksMapper.toBookDTO(savedBook);

    }

    public BooksResponseDTO findById(Integer bookId){
        return booksRepository.findById(bookId)
                .map(booksMapper::toBookDTO)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    public Page<BooksResponseDTO> findAll(Pageable pageable){
        return booksRepository.findAll(pageable)
                .map(booksMapper::toBookDTO);
    }

    private void verifyIfBookIsAlreadyRegistered(BooksRequestDTO booksRequestDTO){
        booksRepository.findByName(booksRequestDTO.getName())
                .ifPresent(duplicatedBook -> {
                    throw new BookAlreadyExistException(booksRequestDTO.getName());
                });
    }

    public void deleteById(Integer bookId){
      BooksEntity foundBookToDelete =  booksRepository.findById(bookId)
                        .orElseThrow(()-> new BookNotFoundException(bookId));
        booksRepository.deleteById(foundBookToDelete.getId());
    }

    public BooksResponseDTO updateById(Integer bookId, BooksRequestDTO booksRequestDTO){
        BooksEntity foundBook  = verifyExist(bookId);
        PublishingEntity foundPublishing = publishingService.findPublishingId(booksRequestDTO.getPublishingId());

        BooksEntity bookToUpdate = booksMapper.toBookModel(booksRequestDTO);
        bookToUpdate.setId(foundBook.getId());
        bookToUpdate.setPublishing(foundPublishing);
        bookToUpdate.setName(foundBook.getName());
        bookToUpdate.setAuthor(foundBook.getAuthor());
        bookToUpdate.setQuantity(foundBook.getQuantity());
        bookToUpdate.setLaunch(foundBook.getLaunch());
        BooksEntity updatedBook = booksRepository.save(bookToUpdate);
        return booksMapper.toBookDTO(updatedBook);

    }

    public BooksEntity verifyExist(Integer bookId){
        return booksRepository.findById(bookId)
                .orElseThrow(()-> new BookNotFoundException(bookId));
    }
}
