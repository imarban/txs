package com.imarban.app;

import com.imarban.app.repository.FileSystemTransactionRepository;
import com.imarban.app.ui.CommandLineInterface;
import com.imarban.app.ui.TransactionUserInterface;

import java.util.Random;

public class MainTest {

    public static void main(String... args) {
        TransactionUserInterface userInterface = new CommandLineInterface(new FileSystemTransactionRepository());
        Integer amount = new Random().nextInt(1000);
        Integer day = 10 + new Random().nextInt(21);
        int userId = 210;


        String transactionJson = String.format("{ \"amount\": %d, \"description\": " +
                "\"Joes Tacos\", \"date\":\"2018-12-%d\", \"user_id\": %d }", amount, day, userId);

        String transactionAdded = userInterface.add(userId, transactionJson);
        System.out.println(transactionAdded);
        System.out.println(userInterface.show(userId,"2d81577c-d084-4997-939a-485daca06239"));
        System.out.println(userInterface.list(userId));
        System.out.println(userInterface.sum(userId));

    }


}
