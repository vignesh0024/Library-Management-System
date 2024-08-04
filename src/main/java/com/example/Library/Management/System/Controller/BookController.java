package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.RequestDto.AddBook;
import com.example.Library.Management.System.ResponseDto.FindBooks;
import com.example.Library.Management.System.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    public BookService bookService;

    @PostMapping("/addbook")
    public ResponseEntity addbook(@RequestBody AddBook request){

        try{

            String result = bookService.addbook(request);
            return new ResponseEntity(result, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/findbooks")
    public ResponseEntity findbooks(@RequestParam("Genre")Genre genre){
        List<FindBooks> response = bookService.findbooks(genre);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
