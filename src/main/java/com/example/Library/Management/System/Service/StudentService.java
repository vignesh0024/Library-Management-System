package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Model.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    public StudentRepository StudentRepository;
    public String addstudent(Student student) throws Exception{

           return null;
    }
}
