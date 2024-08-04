package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Enum.Genre;
import com.example.Library.Management.System.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    List<Book> findBooksByGenre(Genre genre);

    List<Book> findBooksByIsAvailableFalse();


}
