package com.wda.bookstoreManager.exception;

public class DeletePublishingDeniedException extends IllegalArgumentException{

    public DeletePublishingDeniedException(){
        super("This publisher has one or more books registered!");
    }
}
