package com.example.bankapp;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;

    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return customerId == ((Customer) o).customerId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

