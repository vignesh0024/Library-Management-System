package com.example.Library.Management.System.Model;

import com.example.Library.Management.System.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int bookId;

    public Book(String name, Boolean isAvailable, Genre genre, Date publicationDate, Integer price) {
        this.name = name;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.price = price;
    }
    @Column(unique = true)
    public String name;
    public boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    public Genre genre;
    public Date publicationDate;

    public Integer price;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    public Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }
}
