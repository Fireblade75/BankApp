package com.example.bankapp;

import com.example.bankapp.exceptions.InvalidIbanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IbanTest {

    @Test
    public void testCorrectIban() {
        Iban iban = new Iban("NL", 0, "BANK", 2859779760L);
        assertEquals("NL00BANK2859779760", iban.toString());
    }

    @Test
    public void testInvalidCountry() {
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NLX", 0, "BANK", 2859779760L);
        });
    }

    @Test
    public void testNegativeControleGetal() {
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", -5, "BANK", 2859779760L);
        });
    }

    @Test
    public void testTooBigControleGetal() {
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 555, "BANK", 2859779760L);
        });
    }

    @Test
    public void testInvalidBankCode() {
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 55, "BA", 2859779760L);
        });
    }

    @Test
    public void testInvalidRekenningnummer() {
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 55, "BANK", 2859579760L);
        });
    }
}