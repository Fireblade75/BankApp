package com.example.bankapp;

import java.util.ArrayList;

public class Customer {
    private final int customerId;
    private final String firstName;
    private final String lastName;

    public Customer(int customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerId() {
        return customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return customerId == ((Customer) o).customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(customerId);
    }

    @Override
    public String toString() {
        return String.format("%d: %s %s", customerId, firstName, lastName);
    }
}

