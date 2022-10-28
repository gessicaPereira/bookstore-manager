package com.wda.bookstoreManager.exception;

import java.time.DateTimeException;

public class InvalidDateReturnException extends DateTimeException {

    public InvalidDateReturnException(String date){
        super(String.format("The date %s is invalid!", date));
    }
}
