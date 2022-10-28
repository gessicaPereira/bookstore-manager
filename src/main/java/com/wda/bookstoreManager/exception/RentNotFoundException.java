package com.wda.bookstoreManager.exception;

import javax.persistence.EntityNotFoundException;

public class RentNotFoundException extends EntityNotFoundException {

    public RentNotFoundException(Integer rentId){
        super(String.format("Rent %s not found!", rentId));
    }
}
