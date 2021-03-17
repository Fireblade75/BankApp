package com.example.bankapp.cli;

import com.example.bankapp.BankManager;
import com.example.bankapp.Customer;
import com.example.bankapp.Iban;
import com.example.bankapp.exceptions.InvalidIbanException;
import com.example.bankapp.exceptions.UnknownBankAccountException;

import java.util.List;
import java.util.stream.Collectors;

public class BankAccountCommands {
    public static String transfer(BankManager bankManager, String[] words) {
        if (words.length != 4) {
            return "Usage: transfer from[IBAN] to[IBAN] amount[double]";
        } else {
            Iban fromIban = Iban.parseIban(words[1]);
            Iban toIban = Iban.parseIban(words[2]);
            double amount = Double.parseDouble(words[3]);
            bankManager.transferMoney(fromIban, toIban, amount);
            return String.format("Transferred %.02f from %s to %s", amount, fromIban.toString(), toIban.toString());
        }
    }

    public static String createNewAccount(BankManager bankManager, String[] words) {
        if (words.length != 4) {
            return "Usage: createAccount customerId IBAN balance";
        }
        try {
            int customerId = Integer.parseInt(words[1]);
            Iban iban = Iban.parseIban(words[2]);
            double balance = Double.parseDouble(words[3]);

            Customer customer = bankManager.getCustomer(customerId);
            if (customer == null) {
                return "Customer not found";
            }

            bankManager.addAccount(customer, iban, balance);
            return "Created bank account with IBAN " + iban.toString();
        } catch (NumberFormatException e) {
            return "Could not parse numeric input variables";
        } catch (InvalidIbanException e) {
            return "Could not parse the given IBAN";
        }
    }

    public static String getBalance(BankManager bankManager, String[] words) {
        if (words.length != 2) {
            return "Usage: balance IBAN";
        }
        try {
            Iban iban = Iban.parseIban(words[1]);
            double balance = bankManager.getBalance(iban);
            return String.format("%s has a balance of €%.02f", iban.toString(), balance);
        } catch (InvalidIbanException e) {
            return "Could not parse the given IBAN";
        } catch (UnknownBankAccountException e) {
            return "No bank account with given IBAN";
        }
    }

    public static String getAccounts(BankManager bankManager, String[] words) {
        if (words.length != 2) {
            return "Usage: getAccounts customerId";
        }
        try {
            int customerId = Integer.parseInt(words[1]);
            Customer customer = bankManager.getCustomer(customerId);
            if(customer == null) {
                return "Customer not found";
            } else {
                List<Iban> ibans = bankManager.getBankAccountsByCustomer(customer);
                if(ibans.size() > 0) {
                    return "- " + ibans.stream().map(Iban::toString).collect(Collectors.joining("\n- "));
                }
                return "No bank accounts found for " + customer.getFullName();
            }
        } catch (NumberFormatException e) {
            return "Could not parse customer ID";
        }
    }
}
