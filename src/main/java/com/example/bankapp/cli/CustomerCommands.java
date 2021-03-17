package com.example.bankapp.cli;

import com.example.bankapp.BankManager;
import com.example.bankapp.Customer;

public class CustomerCommands {
    public static String createCustomer(BankManager bankManager, String[] words) {
        if (words.length != 3) {
            return "Usage: createCustomer firstName lastName";
        }
        String firstName = words[1];
        String lastName = words[2];
        Customer customer = bankManager.addCustomer(firstName, lastName);
        return String.format("Created customer with id %d", customer.getCustomerId());
    }

    public static String getCustomer(BankManager bankManager, String[] words) {
        if (words.length != 2 && words.length != 3) {
            return "Usage: getCustomer firstName lastName OR getCustomer customerId";
        }
        Customer customer;
        if (words.length == 2) {
            try {
                int customerId = Integer.parseInt(words[1]);
                customer = bankManager.getCustomer(customerId);
            } catch (NumberFormatException e) {
                return "Invalid customerId";
            }
        } else {
            String firstName = words[1];
            String lastName = words[2];
            customer = bankManager.getCustomer(firstName, lastName);
        }
        if (customer != null) {
            return "Customer found: " + customer.toString();
        } else {
            return "Customer not found";
        }
    }
}
