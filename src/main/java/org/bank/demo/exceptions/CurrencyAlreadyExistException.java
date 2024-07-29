package org.bank.demo.exceptions;

import org.bank.demo.beans.Currency;

public class CurrencyAlreadyExistException extends Throwable {
    private Currency existingCurrency;
    public CurrencyAlreadyExistException(Currency existingCurrency) {
        this.existingCurrency = existingCurrency;
    }

    public Currency getExistingCurrency() {
        return existingCurrency;
    }
}
