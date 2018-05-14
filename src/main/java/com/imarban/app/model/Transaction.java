package com.imarban.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction implements Serializable {

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
}