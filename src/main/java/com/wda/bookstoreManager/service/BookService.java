package com.wda.bookstoreManager.service;


import com.wda.bookstoreManager.model.BooksEntity;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BooksResponseDTO createBook(BooksRequestDTO booksRequestDTO);

    BooksResponseDTO getById(Integer bookId);

    Page <BooksResponseDTO> findAll(Pageable pageable);

    void deleteById(Integer bookId);

    BooksResponseDTO updateById(Integer bookId, BooksRequestDTO booksRequestDTO);

    BooksEntity verifyAndGet(Integer bookId);

    void incrementQuantity(BooksEntity books);


}
