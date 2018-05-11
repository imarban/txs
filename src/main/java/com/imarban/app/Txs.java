package com.imarban.app;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

public class Txs {
    public static void main(String... args) {
        ArgumentParser parser = ArgumentParsers.newFor("Txs").build()
                .defaultHelp(true)
                .description("Handle the transactions of a user");
        parser.addArgument("userId").type(Integer.class)
                .help("Id of the user you want to operate in");
        parser.addArgument("operation")
                .choices("add", "list", "sum").nargs("?").setDefault("show")
                .help("Specify operation");
        parser.addArgument("transactionId").type(String.class)
                .help("Id of the transaction");
        Namespace ns = null;
        try {
                ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
                parser.handleError(e);
                System.exit(1);
        }
    }
}