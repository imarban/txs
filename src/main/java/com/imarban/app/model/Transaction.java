package com.imarban.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({ "transaction_id", "amount", "description", "date", "user_id" })
public class Transaction implements Serializable, Comparable<Transaction> {

    @JsonProperty("transaction_id")
    private UUID id;

    private Double amount;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("user_id")
    private Integer userId;

    public Transaction() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public Double getAmount(){
        return this.amount;
    }

    public String getDescription(){
        return this.description;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public Integer getUserId(){
        return this.userId;
    }

    @Override
    public int compareTo(Transaction transaction) {
        return this.date.compareTo(transaction.date);
    }
}