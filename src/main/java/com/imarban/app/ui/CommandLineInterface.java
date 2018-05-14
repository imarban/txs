package com.imarban.app.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.imarban.app.model.Transaction;
import com.imarban.app.repository.TransactionRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandLineInterface implements TransactionUserInterface {

    private TransactionRepository transactionRepository;

    public CommandLineInterface(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public String add(Integer userId, String jsonTransaction) {
        Transaction transaction = parseJsonTransaction(jsonTransaction);
        transactionRepository.add(transaction);
        return parseTransactionJson(transaction);
    }

    @Override
    public String show(Integer userId, String transactionId) {
        Transaction transaction = transactionRepository.show(transactionId, userId);
        if (transaction == null) {
            return "Transaction Not Found";
        }
        return parseTransactionJson(transaction);
    }

    @Override
    public String list(Integer userId) {
        List<Transaction> transactions = transactionRepository.list(userId);
        return "[ \n" + transactions.stream()
                .map(this::parseTransactionJson)
                .collect( Collectors.joining(",\n")) + " \n]";
    }

    @Override
    public String sum(Integer userId) {
        Double total = transactionRepository.sum(userId);
        return String.format("{ user_id: %d, sum: %.2f }", userId, total);
    }


    private Transaction parseJsonTransaction(String transaction) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        try {
            return mapper.readValue(transaction, Transaction.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseTransactionJson(Transaction transaction) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

        try {
            return mapper.writeValueAsString(transaction);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}