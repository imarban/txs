package com.imarban.app.repository;

import java.util.List;
import com.imarban.app.model.Transaction;

public interface TransactionRepository {

    Transaction add(Transaction transaction);
    Transaction show(String transactionId, Integer userId);
    List<Transaction> list(Integer userId);
    Double sum(Integer userId);

}