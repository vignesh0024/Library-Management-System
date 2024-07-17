package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.RequestDto.AddBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public String addbook(AddBook request) {


    }
}
