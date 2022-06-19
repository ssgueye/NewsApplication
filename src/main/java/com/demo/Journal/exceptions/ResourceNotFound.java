package com.demo.Journal.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String errorMessage) {
        super(errorMessage);
    }
}
