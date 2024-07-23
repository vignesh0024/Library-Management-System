package com.example.Library.Management.System.CustomExceptions;

public class BookNotFoundException extends Exception{
    public BookNotFoundException (String message) {
        super(message);
    }
}
