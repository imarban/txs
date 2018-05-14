package com.imarban.app;


import com.imarban.app.repository.FileSystemTransactionRepository;
import com.imarban.app.ui.CommandLineInterface;
import com.imarban.app.ui.TransactionUserInterface;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import net.sourceforge.argparse4j.inf.Subparser;
import net.sourceforge.argparse4j.inf.Subparsers;

public class Txs {
    public static void main(String... args) {
        ArgumentParser commonParser = getCommonArgumentParser();
        try {
            Namespace ns = commonParser.parseArgs(args);
            selectOperation(ns);
            System.exit(0);
        } catch (ArgumentParserException age) {
            handleShowCase(args, age);
        }

    }

    private static void handleShowCase(String[] args, ArgumentParserException originalException) {
        ArgumentParser showParser = getShowArgumentParser();
        try {
            Namespace ns = showParser.parseArgs(args);
            selectOperation(ns);
            System.exit(0);
        } catch (ArgumentParserException e) {
            showParser.handleError(originalException);
            System.exit(-1);
        }
    }

    private static ArgumentParser getCommonArgumentParser() {
        ArgumentParser parser = ArgumentParsers.newFor("Txs").build().setDefault("operation", "show");
        parser.addArgument("userId").type(Integer.class)
                .help("Id of the user you want to operate in");

        Subparsers subparsers = parser.addSubparsers()
                .title("operations")
                .description("valid operations")
                .help("valid operations").dest("operation");

        Subparser addParser = subparsers.addParser("add").help("Add a new transaction");
        addParser.addArgument("transaction").type(String.class).help("Transaction in json format");

        subparsers.addParser("list").help("List the transactions associated with the userId");
        subparsers.addParser("sum").help("Sum all the transactions associated with the userId");
        return parser;
    }

    private static ArgumentParser getShowArgumentParser() {
        ArgumentParser parser = ArgumentParsers.newFor("Txs").build().setDefault("operation", "show");
        parser.addArgument("userId").type(Integer.class)
                .help("Id of the user you want to operate in");

        parser.addArgument("transactionId").help("UUID assigned to the transaction in string format");

        return parser;
    }

    private static void selectOperation(Namespace ns) {
        String operation = ns.getString("operation");
        Integer userId = ns.getInt("userId");
        TransactionUserInterface userInterface = new CommandLineInterface(new FileSystemTransactionRepository());

        switch (operation) {
            case "add":
                System.out.println(userInterface.add(userId, ns.getString("transaction")));
                break;
            case "list":
                System.out.println(userInterface.list(userId));
                break;
            case "sum":
                System.out.println(userInterface.sum(userId));
                break;
            case "show":
                System.out.println(userInterface.show(userId, ns.getString("transactionId")));
                break;

        }
    }

}