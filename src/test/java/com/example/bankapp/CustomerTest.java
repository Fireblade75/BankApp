package com.example.bankapp;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testEquality() {
        Customer customer1 = new Customer(1, "Bob", "Simson");
        Customer customer2 = new Customer(2, "Bob", "Simson");
        Customer customer3 = new Customer(3, "Kim", "Simson");
        Customer customer4 = new Customer(1, "Bob", "Simson");

        assertEquals(customer1, customer4);
        assertEquals(customer1, customer1);
        assertNotEquals(customer1, customer2);
        assertNotEquals(customer1, customer3);

        assertEquals(customer1.hashCode(), customer4.hashCode());
        assertEquals(customer1.hashCode(), customer1.hashCode());
        assertNotEquals(customer1.hashCode(), customer2.hashCode());
        assertNotEquals(customer1.hashCode(), customer3.hashCode());
    }

    @Test
    void testFullName() {
        Customer customer = new Customer(1, "Bob", "Simson");
        assertEquals(customer.getFullName(), "Bob Simson");
    }

    @Test
    void testToString() {
        Customer customer = new Customer(1, "Bob", "Simson");
        assertEquals(customer.toString(), "1: Bob Simson");
    }
}