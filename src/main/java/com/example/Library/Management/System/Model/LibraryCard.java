package com.example.Library.Management.System.Model;

import com.example.Library.Management.System.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="librarycard")
@Getter
@Setter
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int cardNo;
    @Enumerated(value = EnumType.STRING)
    public CardStatus cardStatus;
    public Integer noOfBooksIssued;
    @OneToOne
    @JoinColumn
    public Student student;
    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    public List<Transaction> transactionList = new ArrayList<>();

}
