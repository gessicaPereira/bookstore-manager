package com.wda.bookstoreManager.exception;

public class RentStatusException extends RuntimeException {

    public RentStatusException(String message){
        super(String.format(message));
    }
}
