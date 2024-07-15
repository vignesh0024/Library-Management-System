package com.example.Library.Management.System.Model;

import com.example.Library.Management.System.Enum.Department;
import com.example.Library.Management.System.Enum.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int rollNO;
    public String name;
    public Integer age;
    @Enumerated(value=EnumType.STRING)
    public Gender gender;
    @Enumerated(value=EnumType.STRING)
    public Department department;
    @Column(unique = true)
    public String email;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    public LibraryCard libraryCard;

}
