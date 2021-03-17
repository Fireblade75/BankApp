package com.example.bankapp;

import com.example.bankapp.exceptions.BalanceNotEmptyException;
import com.example.bankapp.exceptions.BankTransferFailedException;
import com.example.bankapp.exceptions.UnknownCustomerException;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private Iban iban;
    private double balance;
    private double interestRate;
    private ArrayList<Customer> customers = new ArrayList<>();

    public BankAccount(Customer customer, Iban iban, double balance, double interestRate) {
        this.iban = iban;
        this.balance = balance;
        this.interestRate = interestRate;
        this.customers.add(customer);
    }

    public Iban getIban() {
        return iban;
    }

    public double getBalance() {
        return balance;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean removeCustomer(Customer customer) throws BalanceNotEmptyException {
        if(!customers.contains(customer)) {
            throw new UnknownCustomerException();
        }
        if(customers.size() == 1 && balance != 0) {
            throw new BalanceNotEmptyException();
        }
        customers.remove(customer);
        return customers.isEmpty();
    }

    public boolean withdraw(double amount) {
        if(amount < 0) {
            throw new BankTransferFailedException();
        }
        if(amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if(amount < 0) {
            throw new BankTransferFailedException();
        }
        balance += amount;
    }

    public double calculateInterest() {
        return interestRate * balance;
    }

    public void applyInterest() {
        this.balance *= 1 + interestRate;
    }

    @Override
    public String toString() {
        return String.format("%s: € %.2f", iban.toString(), balance);
    }

}
