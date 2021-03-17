package com.example.bankapp;

import com.example.bankapp.exceptions.BankTransferFailedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    Customer customer = new Customer(1, "default", "customer");
    private Iban iban = new Iban("NL", 0, "BANK", 2859779760L);

    @Test
    void removeCustomer() {

    }

    @Test
    void successfulWithdraw() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        boolean status = bankAccount.withdraw(400);
        assertTrue(status);
        assertEquals(bankAccount.getBalance(), 400, 0.001);
    }

    @Test
    void failedWithdraw() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        boolean status = bankAccount.withdraw(900);
        assertFalse(status);
        assertEquals(bankAccount.getBalance(), 800, 0.001);
    }

    @Test
    void illegalWithdraw() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        assertThrows(BankTransferFailedException.class, () -> {
            bankAccount.withdraw(-300);
        });
    }

    @Test
    void deposit() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        bankAccount.deposit(400);
        assertEquals(bankAccount.getBalance(), 1200, 0.001);
    }

    @Test
    void illegalDeposit() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        assertThrows(BankTransferFailedException.class, () -> {
            bankAccount.deposit(-300);
        });
    }

    @Test
    void calculateInterest() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.10);
        assertEquals(bankAccount.calculateInterest(), 80, 0.001);
    }

    @Test
    void applyInterest() {
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.10);
        bankAccount.applyInterest();
        assertEquals(bankAccount.getBalance(), 880, 0.001);
    }

    @Test
    void testToString() {
    }
}