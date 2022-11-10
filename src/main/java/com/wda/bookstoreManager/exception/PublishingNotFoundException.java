package com.wda.bookstoreManager.exception;

import javax.persistence.EntityNotFoundException;

public class PublishingNotFoundException extends EntityNotFoundException {

    public PublishingNotFoundException (Integer publishingId){
        super(String.format("Book with id %s not exists!", publishingId));
    }
}
