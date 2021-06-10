package com.example.bankapp;

import com.example.bankapp.exceptions.InvalidIbanException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static Iban parseIban(String ibanString) {
        Pattern pattern = Pattern.compile("([A-Z]{2})(\\d{2})([A-Z]{4})(\\d{10})");
        Matcher matcher = pattern.matcher(ibanString.toUpperCase());
        if(!matcher.matches()) {
            throw new InvalidIbanException("Can't match IBAN with IBAN pattern");
        }

        if(ibanString.length() != 18) {
            throw new InvalidIbanException("Invalid length, length should be 18");
        }
        String landCode = matcher.group(1);
        int controleGetal = Integer.parseInt(matcher.group(2));
        String bankCode = matcher.group(3);
        long rekeningnummer = Long.parseLong(matcher.group(4));

        return new Iban(landCode, controleGetal, bankCode, rekeningnummer);
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

    /**
     * Checks the account number by doing the 11-proof
     * @param accountNumber the number to check
     * @return true if the number is valid
     */
    private boolean checkAccountNumber(long accountNumber) {
        long checkVal = 0;
        String str = String.valueOf(accountNumber);
        for (int i = 0; i < str.length(); i++) {
            checkVal += Long.parseLong(str.substring(i, i+1)) * (str.length() - i);
        }
        return (checkVal % 11) == 0;
    }
}
