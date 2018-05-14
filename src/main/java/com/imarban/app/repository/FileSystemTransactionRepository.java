package com.imarban.app.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imarban.app.model.Transaction;

public class FileSystemTransactionRepository implements TransactionRepository {

	public Transaction add(Transaction transaction) {
        Map<String, Transaction> userTransactions = loadTransactions(transaction.getUserId());
        userTransactions.put(transaction.getId().toString(), transaction);
        saveTransactions(userTransactions, transaction.getUserId());
        return transaction;

	}

	public Transaction show(String transactionId, Integer userId) {
        Map<String, Transaction> userTransactions = loadTransactions(userId);
        return userTransactions.get(transactionId);
	}

	public List<Transaction> list(Integer userId) {
        Map<String, Transaction> userTransactions = loadTransactions(userId);
        ArrayList<Transaction> transactions = new ArrayList<>(userTransactions.values());
        Collections.sort(transactions);

        return transactions;
	}

	public Double sum(Integer userId) {
        Map<String, Transaction> userTransactions = loadTransactions(userId);
        ArrayList<Transaction> transactions = new ArrayList<>(userTransactions.values());

        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
	}

	public Map<String, Transaction> loadTransactions(Integer userId) {
		try (FileInputStream streamIn = new FileInputStream(String.format("%d.ser", userId))) {
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			return (HashMap<String, Transaction>) objectinputstream.readObject();
		} catch (FileNotFoundException fne) {
		    return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return new HashMap<>();
	}

    public void saveTransactions(Map<String, Transaction> transactions, Integer userId) {
        try (FileOutputStream streamOut = new FileOutputStream(String.format("%d.ser", userId))) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(streamOut);
            objectOutputStream.writeObject(transactions);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}