package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
@Query(value = "select * from author where age >=:enterage;",nativeQuery = true)
List<Author> findAuthorByAge (Integer enterage);

}
