package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.Enum.TransactionType;
import com.example.Library.Management.System.Model.Book;
import com.example.Library.Management.System.Model.LibraryCard;
import com.example.Library.Management.System.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(Book book, LibraryCard card, TransactionStatus transactionStatus, TransactionType transactionType);
}
