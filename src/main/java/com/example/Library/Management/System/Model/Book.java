package com.example.Library.Management.System.Model;

import com.example.Library.Management.System.Enum.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookId;
    public String name;
    public boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    public Genre genre;
    public Date publicationDate;
    public Integer price;

    @ManyToOne
    @JoinColumn
    public Author author;


}
