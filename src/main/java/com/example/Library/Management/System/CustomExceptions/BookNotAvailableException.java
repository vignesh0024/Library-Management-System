package com.example.Library.Management.System.CustomExceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException (String message){
        super(message);
    }
}
