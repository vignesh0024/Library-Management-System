package com.example.Library.Management.System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer authorId;

    public String name;

    public String emailId;

    public Integer age;

    public String penName;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    public List<Book> bookList= new ArrayList<>();


}
