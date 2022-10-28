package com.wda.bookstoreManager.exception;

import javax.persistence.EntityExistsException;

public class RentAlreadyExistException extends EntityExistsException {

    public RentAlreadyExistException(Object books, Object users){
        super(String.format("Rent with %d already exists", books, users));
    }
}
