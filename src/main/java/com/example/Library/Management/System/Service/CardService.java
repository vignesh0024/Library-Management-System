package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Model.LibraryCard;
import com.example.Library.Management.System.Model.Student;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    public CardRepository cardRepository;

    @Autowired

    public StudentRepository studentRepository;

    public String addcard(LibraryCard card){

        cardRepository.save(card);
        return "Card Added Successfully";
    }

    public String linktostudent(int cardNo,Integer rollNo) throws Exception{

        Optional<Student> studentoptional = studentRepository.findById(rollNo);
        Student student=studentoptional.get();

        Optional<LibraryCard> cardoptional = cardRepository.findById(rollNo);
        LibraryCard card=cardoptional.get();

        if(!studentRepository.existsById(rollNo)){
            throw new Exception("rollNo not exists");
        }

        if(!cardRepository.existsById(cardNo)){
            throw new Exception("cardNo not exists");
        }

        card.setStudent(student);

        student.setLibraryCard(card);

        studentRepository.save(student);

        return "Card " +cardNo+ "linked to " +rollNo+ "Successfully";

    }
}
