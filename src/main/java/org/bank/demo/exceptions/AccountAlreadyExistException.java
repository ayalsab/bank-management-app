package org.bank.demo.exceptions;

import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Customer;

public class CustomerAlreadyExistException extends Throwable {
    private Customer existingCustomer;
    public CustomerAlreadyExistException(Customer existingCustomer) {
        this.existingCustomer = existingCustomer;
    }

    public Customer getExistingCustomer() {
        return existingCustomer;
    }
}
