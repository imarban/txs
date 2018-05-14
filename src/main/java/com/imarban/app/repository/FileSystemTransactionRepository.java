package com.imarban.app.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.imarban.app.model.Transaction;

public class FileSystemTransactionRepository implements TransactionRepository {

	public void add(Transaction transaction) {
        List<Transaction> userTransactions = loadTransactions(transaction.getUserId());
        if (userTransactions == null) {
            userTransactions = new ArrayList<>();
        }
        userTransactions.add(transaction);
        saveTransactions(userTransactions);

	}

	public Transaction show(String transactionId, Integer userId) {
        List<Transaction> userTransactions = loadTransactions(userId);
        return userTransactions.get(0);
	}

	public List<Transaction> list(Integer userId) {
		return null;
	}

	public Double sum(Integer userId) {
		return null;
	}

	private List<Transaction> loadTransactions(Integer userId) {
		try (FileInputStream streamIn = new FileInputStream(String.format("%d.ser", userId))) {
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			return (List<Transaction>) objectinputstream.readObject();
		} catch (FileNotFoundException fne) {
		    return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	private void saveTransactions(List<Transaction> transactions) {
	    Integer userId = transactions.get(0).getUserId();
        try (FileOutputStream streamOut = new FileOutputStream(String.format("%d.ser", userId))) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(streamOut);
            objectOutputStream.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}