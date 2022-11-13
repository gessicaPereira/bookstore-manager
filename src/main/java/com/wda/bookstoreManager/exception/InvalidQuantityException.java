package com.wda.bookstoreManager.exception;

public class InvalidQuantityException extends IllegalArgumentException{

    public InvalidQuantityException(){
        super("Quantity invalid");
    }
}
