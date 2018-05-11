package com.imarban.app.model;

import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

    private UUID id;

    private Double amount;
    private String description;
    private LocalDate date;
    private Integer userId;

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
}