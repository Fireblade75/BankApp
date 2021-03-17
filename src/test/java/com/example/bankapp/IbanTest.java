package com.example.bankapp;

import com.example.bankapp.exceptions.InvalidIbanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IbanTest {

    @Test
    public void testCorrectIban() {
        // Given a valid IBAN with the properties "NL" "0" "Bank" and "2859779760L"
        Iban iban = new Iban("NL", 0, "BANK", 2859779760L);
        // When a string is made out of the IBAN
        // Then the String should be "NL00BANK2859779760"
        assertEquals("NL00BANK2859779760", iban.toString());
    }

    @Test
    public void testInvalidCountry() {
        // When an IBAN is made with an invalid country code
        // Then an error is thrown
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NLX", 0, "BANK", 2859779760L);
        });
    }

    @Test
    public void testNegativeControleGetal() {
        // When an IBAN is made with a negative controle getal
        // Then an error is thrown
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", -5, "BANK", 2859779760L);
        });
    }

    @Test
    public void testTooBigControleGetal() {
        // When an IBAN is made with a controle getal that is too big
        // Then an error is thrown
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 555, "BANK", 2859779760L);
        });
    }

    @Test
    public void testInvalidBankCode() {
        // When an IBAN is made with an invalid bank code
        // Then an error is thrown
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 55, "BA", 2859779760L);
        });
    }

    @Test
    public void testInvalidRekeningnummer() {
        // When an IBAN is made with an invalid rekeningnummer
        // Then an error is thrown
        assertThrows(InvalidIbanException.class, () -> {
            Iban iban = new Iban("NL", 55, "BANK", 2859579760L);
        });
    }

    @Test
    void parseIban() {
        // When a correct IBAN is parsed
        Iban iban = Iban.parseIban("NL00BANK2859779760");
        // Then a print of that IBAN should match the input
        assertEquals(iban.toString(), "NL00BANK2859779760");
    }
}