package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Model.Author;
import com.example.Library.Management.System.RequestDto.UpdateNameAndPenName;
import com.example.Library.Management.System.Service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
@Slf4j
public class AuthorController {

    @Autowired
    public AuthorService authorService;

    @PostMapping("/addauthor")
    public ResponseEntity addauthor(@RequestBody Author author) {
            try{
                String result= authorService.addauthor(author);
                return new ResponseEntity(result, HttpStatus.OK);
            }catch(Exception e){
                log.error("unbale to add author {}",e.getMessage());
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/updatename")
    public String updatename(@RequestBody UpdateNameAndPenName updateName){
        try{
            String result = authorService.updatename(updateName);
            return result;

        }catch (Exception e){
            return "Author Id is invalid"+e.getMessage();
        }
    }

}
