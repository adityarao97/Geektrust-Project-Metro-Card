package com.geektrust.backend.exceptions;

public class DuplicateMetroCardException extends RuntimeException{
        public DuplicateMetroCardException(String msg){
        super(msg);
    }
}
