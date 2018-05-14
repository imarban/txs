package com.imarban.app.ui;

import com.imarban.app.model.Transaction;
import com.imarban.app.repository.TransactionRepository;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class CommandLineInterfaceTest {

    private CommandLineInterface commandLineInterface;
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        commandLineInterface = new CommandLineInterface(transactionRepository);
    }

    @Test
    public void testAdd() {
        double amount = 3.14;
        String description = "Joes Tacos";
        String date = "2018-12-30";
        Integer userId = 12;

        String input = String.format("{ \"amount\": %.2f, \"description\": \"%s\", \"date\": \"%s\", \"user_id\": %d }",
                amount, description, date, userId);

        Transaction transactionAdded = new Transaction(amount, description, LocalDate.of(2018, 12, 30), userId);
        Mockito.when(transactionRepository.add(Mockito.any(Transaction.class))).thenReturn(transactionAdded);
        String actualOutput = commandLineInterface.add(userId, input);

        String expectedOutput = String.format("{\"transaction_id\":\"%s\",\"amount\":%.2f,\"description\":" +
                        "\"%s\",\"date\":\"%s\",\"user_id\":%d}", transactionAdded.getId().toString(),
                amount, description, date, userId);

        MatcherAssert.assertThat("Output does not match expected", expectedOutput.equals(actualOutput));


    }

    @Test
    public void testSum() {
        int userId = 10;
        double total = 30.0;
        Mockito.when(transactionRepository.sum(userId)).thenReturn(total);
        String actualOutput = commandLineInterface.sum(userId);

        String expectedOutput = String.format("{ \"user_id\": %d, \"sum\": %.2f }", userId, total);
        MatcherAssert.assertThat("Output does not match expected", expectedOutput.equals(actualOutput));
    }

}
