package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Model.Transaction;
import com.example.Library.Management.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    public TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestParam("/bookId") int bookId,@RequestParam("/cardNo") int cardNo){
        try{
            String result = transactionService.add(bookId,cardNo);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

}
