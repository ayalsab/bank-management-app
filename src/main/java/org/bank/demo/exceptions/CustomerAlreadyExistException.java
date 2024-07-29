package org.bank.demo.exceptions;

import org.bank.demo.beans.Currency;

public class CurrencyAlreadyExistException extends Throwable {
    private Currency existingCurrency;
    public CurrencyAlreadyExistException(Currency existingStudent) {
        this.existingCurrency = existingStudent;
    }

    public Currency getExistingCurrency() {
        return existingCurrency;
    }
}
