package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Enum.Department;
import com.example.Library.Management.System.Model.Student;
import com.example.Library.Management.System.Service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    public StudentService StudentService;
    @PostMapping("/addstudent")
    public ResponseEntity addstudent(@RequestBody Student student){
        try{
            String result = StudentService.addstudent(student);
            return new ResponseEntity("User added successfully", HttpStatus.OK);

        }catch (Exception e){
            log.error("Unable to add user  "+ e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping("/finddepbyid")
    public ResponseEntity finddepbyid(@RequestParam("rollNo") int rollNo){
        try{
            Department result = StudentService.finddepbyid(rollNo);
            return new ResponseEntity(result,HttpStatus.OK);
        }catch(Exception e){
            log.error("Unable to find user {} ",e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
