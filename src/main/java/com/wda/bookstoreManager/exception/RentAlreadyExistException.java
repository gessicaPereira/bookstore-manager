package com.wda.bookstoreManager.exception;

import javax.persistence.EntityExistsException;

public class RentAlreadyExistException extends EntityExistsException {

    public RentAlreadyExistException(Integer id){
        super(String.format("Rent with %d already exists", id));
    }
}
