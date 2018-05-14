package com.imarban.app.repository;

import com.imarban.app.model.Transaction;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class FileSystemTransactionRepositoryTest {

    private FileSystemTransactionRepository fileSystemTransactionRepository;

    @Before
    public void setUp() {
        fileSystemTransactionRepository = Mockito.mock(FileSystemTransactionRepository.class);
    }


    @Test
    public void testAdd() {
        Transaction transactionToAdd = new Transaction(6.28, "This is not a description",
                LocalDate.MIN, 10);
        Mockito.when(fileSystemTransactionRepository.loadTransactions(Mockito.anyInt())).thenReturn(new HashMap<>());
        Mockito.when(fileSystemTransactionRepository.add(transactionToAdd)).thenCallRealMethod();
        Transaction transactionReturned = fileSystemTransactionRepository.add(transactionToAdd);
        MatcherAssert.assertThat("Transaction id is not present", transactionToAdd.getId() != null);
        MatcherAssert.assertThat("Transaction added is not correct",
                transactionToAdd.getUserId().equals(transactionReturned.getUserId()) &&
                        transactionToAdd.getAmount().equals(transactionReturned.getAmount()) &&
                        transactionToAdd.getDate().equals(transactionReturned.getDate()) &&
                        transactionToAdd.getDescription().equals(transactionReturned.getDescription())
        );

    }

    @Test
    public void testSum() {
        int userId = 10;
        Transaction transaction1 = new Transaction(3.14, "This is not a description",
                LocalDate.MIN, userId);

        Transaction transaction2 = new Transaction(3.14, "This is not a description",
                LocalDate.MIN, userId);

        Map<String, Transaction> transactionMap  = createTransactionMap(transaction1, transaction2);

        Mockito.when(fileSystemTransactionRepository.loadTransactions(Mockito.anyInt())).thenReturn(transactionMap);
        Mockito.when(fileSystemTransactionRepository.sum(userId)).thenCallRealMethod();
        Double sum = fileSystemTransactionRepository.sum(userId);
        Double expectedResult = transaction1.getAmount() + transaction2.getAmount();
        MatcherAssert.assertThat("Sum is not correct", expectedResult.equals(sum));

    }

    private Map<String, Transaction> createTransactionMap(Transaction... transactions) {

        Map<String, Transaction> transactionMap = new HashMap<>();
        for (Transaction transaction: transactions) {
            transactionMap.put(transaction.getId().toString(), transaction);
        }
        return transactionMap;
    }

}
