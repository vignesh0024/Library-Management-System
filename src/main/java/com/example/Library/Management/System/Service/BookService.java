package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Model.Author;
import com.example.Library.Management.System.Model.Book;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.RequestDto.AddBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    public AuthorRepository authorRepository;
    @Autowired
    public BookRepository bookRepository;

    public String addbook(AddBook request) throws Exception {

        Optional<Author> authorOptional = authorRepository.findById(request.authorId);
        if(!authorOptional.isPresent()){
            throw new Exception("Author is not valid ");
        }

        Author authorobj=authorOptional.get();
        Book bookobj=new Book(request.getName(),request.getIsAvailable(),request.getGenre(),request.getPublicationDate(),request.getPrice());

       //Since it's a bidirectional : need to set both in child and parent class

        //Set the parent entity in child class

        bookobj.setAuthor(authorobj);

        //Set the child entity in parent class
        List<Book> list=authorobj.getBookList();
        list.add(bookobj);
        //authorobj.setBookList(list);

        authorRepository.save(authorobj);
        return "Book has been successfully added and updated";






    }
}
