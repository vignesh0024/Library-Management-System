package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Model.Author;
import com.example.Library.Management.System.Repository.AuthorRepository;
import com.example.Library.Management.System.RequestDto.UpdateNameAndPenName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    public AuthorRepository authorRepository;

    public String addauthor(Author author) throws Exception{

        if(author.getAuthorId()!=null){
            throw new Exception("Author id should be passed as a parameter");
        }
        authorRepository.save(author);
        return "Author Saved Successfully";
    }

    public String updatename(UpdateNameAndPenName request) throws Exception {

        Optional<Author> authorOptional = authorRepository.findById(request.getAuthorId());

        if(!authorOptional.isPresent()){
            throw new Exception("Entered author is invalid");
        }

        Author authorobj=authorOptional.get();
        authorobj.setName(request.getNewName());
        authorobj.setPenName(request.getNewPenName());

        authorRepository.save(authorobj);
        return "Author Name and PenName has been";

    }


    public Author getauthor(Integer authorid) {
        Author author = authorRepository.findById(authorid).get();
        return author;
    }
}
