package com.wda.bookstoreManager.exception;

public class DeleteNotPossibleException extends RuntimeException{

    public DeleteNotPossibleException(String message){
        super(message);
    }
}
