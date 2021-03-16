package com.example.bankapp;

import com.example.bankapp.exceptions.InvalidIbanException;

import java.util.Objects;

public class Iban {
    private String landCode;
    private int controleGetal;
    private String bankCode;
    private long rekeningnummer;

    public Iban(String landCode, int controleGetal, String bankCode, long rekeningnummer) {
        this.landCode = landCode;
        this.controleGetal = controleGetal;
        this.bankCode = bankCode;
        this.rekeningnummer = rekeningnummer;

        if(landCode.length() != 2) {
            throw new InvalidIbanException("Invalid Landcode");
        }
        if(controleGetal < 0 || controleGetal > 99) {
            throw new InvalidIbanException("Invalid controle getal");
        }
        if(bankCode.length() != 4) {
            throw new InvalidIbanException("Invalid Bankcode");
        }
        if(this.rekeningnummer > 9999999999L || !checkAccountNumber(this.rekeningnummer)) {
            throw new InvalidIbanException("Invalid Rekeningnummer");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iban iban = (Iban) o;
        return iban.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(landCode, controleGetal, bankCode, rekeningnummer);
    }

    @Override
    public String toString() {
        return String.format("%s%02d%s%09d", landCode, controleGetal, bankCode, rekeningnummer);
    }

    public static boolean checkAccountNumber(long accountNumber) {
        long checkVal = 0;
        String str = String.valueOf(accountNumber);
        for (int i = 0; i < str.length(); i++) {
            checkVal += Long.parseLong(str.substring(i, i+1)) * (str.length() - i);
        }
        return (checkVal % 11) == 0;
    }
}
