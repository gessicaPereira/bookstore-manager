package com.wda.bookstoreManager.exception;

import javax.persistence.EntityExistsException;

public class BookAlreadyExistException extends EntityExistsException {

    public BookAlreadyExistException(String name){
        super(String.format("Book with name %s already registered", name));
    }
}
