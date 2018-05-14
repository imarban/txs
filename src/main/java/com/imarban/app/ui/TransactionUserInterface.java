package com.imarban.app.ui;

public interface TransactionUserInterface {
    void add(Integer userId, String transaction);

    String show(Integer userId, String transactionId);

    String list(Integer userId);

    String sum(Integer userId);
}
