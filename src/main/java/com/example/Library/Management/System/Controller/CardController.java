package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Model.LibraryCard;
import com.example.Library.Management.System.Service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {

    @Autowired
    public CardService cardService;

    @PostMapping("/addcard")
    public String addcard(@RequestBody LibraryCard card){
          return cardService.addcard(card);
    }

    @PutMapping("/linktostudent")
    public ResponseEntity linktostudent(@RequestParam("cardNo") int cardNo,@RequestParam("rollNo") Integer rollNo){
        try{
            String result=cardService.linktostudent(cardNo,rollNo);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(Exception e){
             log.error("Card link to student cannot be established {}",e.getMessage());
             return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
