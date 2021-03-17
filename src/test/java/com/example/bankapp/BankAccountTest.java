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
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        // When a withdrawal is made of less then the current balance
        boolean status = bankAccount.withdraw(400);
        // Then the status of the withdrawal should be successful
        assertTrue(status);
        // And the balance of the account should be 400
        assertEquals(bankAccount.getBalance(), 400, 0.001);
    }

    @Test
    void failedWithdraw() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        // When a withdrawal is made of more then the current balance
        boolean status = bankAccount.withdraw(900);
        // Then the status of the withdrawal should be failed
        assertFalse(status);
        // And the balance of the account should be 800
        assertEquals(bankAccount.getBalance(), 800, 0.001);
    }

    @Test
    void illegalWithdraw() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        // When a withdrawal is made with a negative amount
        // Then an error should be thrown
        assertThrows(BankTransferFailedException.class, () -> {
            bankAccount.withdraw(-300);
        });
    }

    @Test
    void deposit() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        // When a deposit is made of less then the current balance
        bankAccount.deposit(400);
        assertEquals(bankAccount.getBalance(), 1200, 0.001);
    }

    @Test
    void illegalDeposit() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.05);
        // When a deposit is made with a negative amount
        // Then an error should be thrown
        assertThrows(BankTransferFailedException.class, () -> {
            bankAccount.deposit(-300);
        });
    }

    @Test
    void calculateInterest() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.10);
        // When the interest for one year is calculated
        double interest = bankAccount.calculateInterest();
        // Then the interest should be 80
        assertEquals(interest, 80, 0.001);
    }

    @Test
    void applyInterest() {
        // Given an existing bank account is made with a balance of "800"
        BankAccount bankAccount = new BankAccount(customer, iban, 800, 0.10);
        // When the interest for one year is applied
        bankAccount.applyInterest();
        // Then the balance of the bank account should be 880
        assertEquals(bankAccount.getBalance(), 880, 0.001);
    }

    @Test
    void testToString() {
    }
}