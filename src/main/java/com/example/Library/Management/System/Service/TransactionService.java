package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.CustomExceptions.BookNotAvailableException;
import com.example.Library.Management.System.CustomExceptions.BookNotFoundException;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.Enum.TransactionType;
import com.example.Library.Management.System.Model.Book;
import com.example.Library.Management.System.Model.LibraryCard;
import com.example.Library.Management.System.Model.Transaction;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public CardRepository cardRepository;

    @Autowired
    public TransactionRepository transactionRepository;

    @Value("${book.maxLimit}")
    private Integer maxBookLimit;


    public String add(int bookId, int cardNo) throws Exception {

        Transaction transaction = new Transaction(TransactionStatus.PENDING, TransactionType.ISSUE,0);

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(!bookOptional.isPresent()){
            throw new BookNotFoundException("Unable to find the book");
        }
        Book bookobj = bookOptional.get();
        if(bookobj.isAvailable=Boolean.FALSE) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionType(TransactionType.ERROR);
            transactionRepository.save(transaction);
            throw new BookNotAvailableException("Book is checked out already and not available");
        }

        Optional<LibraryCard> cardOptional = cardRepository.findById(cardNo);
        if(!cardOptional.isPresent()){
            throw new Exception("Unable to find library card");
        }

        LibraryCard cardobj= cardOptional.get();

        if(cardobj.getNoOfBooksIssued() > maxBookLimit){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionType(TransactionType.ERROR);
            transactionRepository.save(transaction);
            throw new Exception("card Limit exceeded for the book checkout");
        }

        if(cardobj.getCardStatus().equals(CardStatus.ACTIVE)){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setTransactionType(TransactionType.ERROR);
            transactionRepository.save(transaction);
            throw new Exception("Card status is not active");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        bookobj.setIsAvailable(Boolean.FALSE);
        cardobj.setNoOfBooksIssued(cardobj.getNoOfBooksIssued()+1);

        //unidrectional mapping
        transaction.setBook(bookobj);
        transaction.setLibraryCard(cardobj);

        //Bidirectional mapping
        Transaction transactionwithid = transactionRepository.save(transaction);
        cardobj.getTransactionList().add(transactionwithid);
        bookobj.getTransactionList().add(transactionwithid);

        //below is not mandatory since the above transaction .save will call both card & book save due to cascading effect
        bookRepository.save(bookobj);
        cardRepository.save(cardobj);

        return "Transaction has been saved successfully";

    }

    public String retrunbook(Integer bookId,Integer cardId){

        Book bookobj = bookRepository.findById(bookId).get();
        LibraryCard cardobj = cardRepository.findById(cardId).get();

        List<Transaction> transactionList = transactionRepository.findTransactionsByBookAndLibraryCardAndTransactionStatusAndTransactionType(bookobj,cardobj,TransactionStatus.SUCCESS,TransactionType.ISSUE);

        Transaction latestTransaction = transactionList.get(transactionList.size()-1);

        Date issuedate = latestTransaction.getCreatedAt();

        long milliseconds = Math.abs(System.currentTimeMillis() - issuedate.getTime());
        long no_of_days = TimeUnit.DAYS.convert(milliseconds,TimeUnit.MILLISECONDS);

        int fineamount =0;
        if(no_of_days > 15){
            fineamount= (int) ((no_of_days - 15) * 5);
        }

        bookobj.setIsAvailable (Boolean.FALSE);
        cardobj.setNoOfBooksIssued(cardobj.getNoOfBooksIssued() - 1);

        Transaction transationobj = new Transaction(TransactionStatus.SUCCESS,TransactionType.RETURN,fineamount);

        transationobj.setBook(bookobj);
        transationobj.setLibraryCard(cardobj);

        Transaction transactionobjwithid = TransactionRepository.save(transationobj);

        bookobj.getTransactionList().add(transactionobjwithid);
        cardobj.getTransactionList().add(transactionobjwithid);

        //Saving the parents
        bookRepository.save(book);
        cardRepository.save(card);

        return "Book has successfully been returned";

    }
}
