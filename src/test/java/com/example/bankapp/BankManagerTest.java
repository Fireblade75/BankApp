package com.example.bankapp;

import com.example.bankapp.exceptions.BankTransferFailedException;
import com.example.bankapp.exceptions.UnknownBankAccountException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankManagerTest {

    @Test
    public void testPrint() {
        BankManager bankManager = new BankManager();
        Customer customer = bankManager.addCustomer("Bob", "Jansen");
        Iban iban = bankManager.addAccount(customer, 3100072456L, 480);
        assertEquals(bankManager.getBalance(iban), 480, 0.001);
        double interest = bankManager.getInterestRate() * 480;
        String expectedText = String.format("NL00BANK3100072456: € 480,00 - Interest next year: %.02f%n", interest);
        assertEquals(bankManager.toString(), expectedText);

    }

    @Test
    public void testWithdraw() {
        BankManager bankManager = new BankManager();
        Customer customer = bankManager.addCustomer("Bob", "Jansen");
        Iban iban1 = bankManager.addAccount(customer, 3100072456L, 480);
        Iban iban2 = bankManager.addAccount(customer, 8877477636L, 200);
        bankManager.transferMoney(iban1, iban2, 400);
        System.out.println(bankManager.toString());
    }

    @Test
    public void testWithdrawEqual() {
        BankManager bankManager = new BankManager();
        Customer customer = bankManager.addCustomer("Bob", "Jansen");
        Iban iban1 = bankManager.addAccount(customer, 3100072456L, 480);
        Iban iban2 = bankManager.addAccount(customer, 8877477636L, 200);
        bankManager.transferMoney(iban1, iban2, 480);
        System.out.println(bankManager.toString());
    }

    @Test
    public void testWithdrawTooMuch() {
        BankManager bankManager = new BankManager();
        Customer customer = bankManager.addCustomer("Bob", "Jansen");
        Iban iban1 = bankManager.addAccount(customer, 4520711568L, 280);
        Iban iban2 = bankManager.addAccount(customer, 8877477636L, 200);
        assertThrows(BankTransferFailedException.class, () -> {
            bankManager.transferMoney(iban1, iban2, 400);
            System.out.println(bankManager.toString());
        });
    }

    @Test
    public void testWithdrawNonExistent() {
        BankManager bankManager = new BankManager();
        Customer customer = bankManager.addCustomer("Kim", "Simson");
        Iban existingIban = bankManager.addAccount(customer ,8877477636L, 200);
        Iban fakeIban = new Iban("NL", 0, "FAKE", 2859779760L);

        assertThrows(UnknownBankAccountException.class, () -> {
            bankManager.transferMoney(fakeIban, existingIban, 400);
            System.out.println(bankManager.toString());
        });
    }
}