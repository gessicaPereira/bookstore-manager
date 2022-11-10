package com.wda.bookstoreManager.exception;

public class DeleteDeniedException extends IllegalArgumentException{

    public DeleteDeniedException(){
        super("user has one or more rented books");
    }
}
