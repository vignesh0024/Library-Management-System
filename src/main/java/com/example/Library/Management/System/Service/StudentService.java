package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Enum.Department;
import com.example.Library.Management.System.Model.Student;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    public StudentRepository StudentRepository;
    public String addstudent(Student student) throws Exception{

        if(student.getRollNo()!=null){
            throw new Exception("ID already Exist/ID should not be sent as parameter");
        }
        StudentRepository.save(student);

        return "Student "+student.getName()+" added successfully";
    }

    public Department finddepbyid(int rollNo) throws Exception {
        Optional<Student> studentOptional = StudentRepository.findById(rollNo);

        if(!studentOptional.isPresent()){
            throw new Exception("RollNo entered is incorrect or not available");
        }
        Student student=studentOptional.get();
        return student.getDepartment();
    }
}
