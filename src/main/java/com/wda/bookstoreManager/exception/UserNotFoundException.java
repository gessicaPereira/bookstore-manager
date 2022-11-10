package com.wda.bookstoreManager.exception;

import javax.persistence.EntityExistsException;

public class UserNotFoundException extends EntityExistsException {

    public UserNotFoundException (Integer userId){
        super(String.format("User with id %s not exists!", userId));
    }
}
