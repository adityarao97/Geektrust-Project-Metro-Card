package com.geektrust.backend.exceptions;

public class MetroCardDoesNotExistException extends RuntimeException{
    public MetroCardDoesNotExistException(String msg){
        super(msg);
    }
}
