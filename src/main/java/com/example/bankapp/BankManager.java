package com.example.bankapp;

import com.example.bankapp.exceptions.BankTransferFailedException;
import com.example.bankapp.exceptions.UnknownBankAccountException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BankManager {
    private static final double INTEREST_RATE = 0.05;
    private static final String COUNTRY_CODE = "NL";
    private static final String BANK_CODE = "BANK";
    HashMap<Iban, BankAccount> bankAccounts = new HashMap<>();
    ArrayList<Customer> customers = new ArrayList<>();
    static int nextCustomerId = 0;

    public Customer addCustomer(String firstName, String lastName) {
        Customer customer = new Customer(nextCustomerId++, firstName, lastName);
        customers.add(customer);
        return customer;
    }

    public Iban addAccount(Customer customer, long accountNumber, double balance) {
        Iban iban = new Iban(COUNTRY_CODE, 0, BANK_CODE, accountNumber);
        bankAccounts.put(iban, new BankAccount(customer, iban, balance, INTEREST_RATE));
        return iban;
    }

    public void transferMoney(Iban from, Iban to, double amount) {
        BankAccount fromAccount = bankAccounts.get(from);
        BankAccount toAccount = bankAccounts.get(to);
        if(fromAccount == null || toAccount == null) {
            throw new UnknownBankAccountException();
        } else {
            if(fromAccount.withdraw(amount)) {
                toAccount.deposit(amount);
            } else {
                throw new BankTransferFailedException();
            }
        }
    }

    public void applyInterest() {
        for(Map.Entry<Iban, BankAccount> bankAccountEntry : bankAccounts.entrySet()) {
            bankAccountEntry.getValue().applyInterest();
        }
    }

    public double getBalance(Iban accountNumber) {
        BankAccount bankAccount = bankAccounts.get(accountNumber);
        if(bankAccount == null) {
            throw new UnknownBankAccountException();
        } else {
            return bankAccount.getBalance();
        }
    }

    public double getInterestRate() {
        return INTEREST_RATE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Iban, BankAccount> bankAccountEntry : bankAccounts.entrySet()) {
            BankAccount bankAccount = bankAccountEntry.getValue();
            sb.append(String.format("%s - Interest next year: %.2f%n", bankAccount.toString(), bankAccount.calculateInterest()));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BankManager bankManager = new BankManager();
        Random r = new Random();
        long[] bankNumbers = new long[]{ 7830770891L, 5595284773L, 9054943327L, 8966820263L };

        for(long bankNumber : bankNumbers) {
            Customer c = bankManager.addCustomer("x", "x");
            bankManager.addAccount(c, bankNumber, r.nextInt(800) + 200);
        }

        System.out.println(bankManager.toString());
        bankManager.applyInterest();
        System.out.println(bankManager.toString());
    }
}
