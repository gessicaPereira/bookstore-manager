package com.wda.bookstoreManager.exception;

import javax.persistence.EntityNotFoundException;

public class BookNotFoundException extends EntityNotFoundException {

    public BookNotFoundException(Integer bookId){
        super(String.format("Book with id %s not exists!", bookId));
    }
}
