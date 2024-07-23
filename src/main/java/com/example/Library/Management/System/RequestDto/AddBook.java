package com.example.Library.Management.System.RequestDto;

import com.example.Library.Management.System.Enum.Genre;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddBook{

    public String name;
    public Boolean isAvailable;
    public Genre genre;
    public Date publicationDate;
    public Integer price;
    public Integer authorId;

}

