package com.example.bankapp.cli;

import com.example.bankapp.BankManager;

public class CommandHandler {
    BankManager bankManager = new BankManager();

    public String handleCommand(String line) {
        String[] words = line.split(" ");
        if (words.length == 0) {
            return "";
        }

        switch (words[0]) {
            case "exit":
                return "exit";
            case "createCustomer":
                return CustomerCommands.createCustomer(bankManager, words);
            case "getCustomer":
                return CustomerCommands.getCustomer(bankManager, words);
            case "createAccount":
                return BankAccountCommands.createNewAccount(bankManager, words);
            case "transfer":
                return BankAccountCommands.transfer(bankManager, words);
            case "balance":
                return BankAccountCommands.getBalance(bankManager, words);
            case "getAccounts":
                return BankAccountCommands.getAccounts(bankManager, words);
            default:
                return "Unknown command";
        }
    }



}
