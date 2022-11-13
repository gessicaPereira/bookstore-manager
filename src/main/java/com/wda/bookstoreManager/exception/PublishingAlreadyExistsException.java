package com.wda.bookstoreManager.exception;

import javax.persistence.EntityExistsException;

public class PublishingAlreadyExistsException extends EntityExistsException {

    public PublishingAlreadyExistsException(String name){
        super(String.format("publishing with name %s already exists", name));
    }
}
