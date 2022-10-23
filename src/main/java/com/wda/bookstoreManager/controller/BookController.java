package com.wda.bookstoreManager.controller;

import com.wda.bookstoreManager.mapper.BooksMapper;
import com.wda.bookstoreManager.model.DTO.BooksRequestDTO;
import com.wda.bookstoreManager.model.DTO.BooksResponseDTO;
import com.wda.bookstoreManager.repository.BooksRepository;
import com.wda.bookstoreManager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private BookService bookService;

    private BooksRepository booksRepository;

    private BooksMapper booksMapper;

    @Autowired
    public BookController(BookService bookService, BooksRepository booksRepository){
        this.bookService = bookService;
        this.booksRepository = booksRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooksResponseDTO createBook(@RequestBody BooksRequestDTO booksRequestDTO){
        return bookService.createBook(booksRequestDTO);
    }

    @GetMapping("/{bookId}")
    public BooksResponseDTO findById(@PathVariable Integer bookId) {
        return bookService.findById(bookId);
    }

    @GetMapping
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer bookId) {
        bookService.deleteById(bookId);
    }

    @PutMapping("/{bookId}")
    public BooksResponseDTO updateById(@PathVariable Integer bookId, @RequestBody BooksRequestDTO booksRequestDTO) {
        return bookService.updateById(bookId, booksRequestDTO);
    }
}
