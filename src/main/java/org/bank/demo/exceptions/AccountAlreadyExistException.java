package org.bank.demo.exceptions;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Customer;

public class AccountAlreadyExistException extends Throwable {
    private Account existingAccount;
    public AccountAlreadyExistException(Account existingAccount) {
        this.existingAccount = existingAccount;
    }

    public Account getExistingAccount() {
        return existingAccount;
    }
}
