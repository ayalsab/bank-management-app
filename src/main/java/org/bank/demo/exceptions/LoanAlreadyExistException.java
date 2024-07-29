package org.bank.demo.exceptions;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;

public class LoanAlreadyExistException extends Throwable {
    private Loan existingLoan;
    public LoanAlreadyExistException(Loan existingLoan) {
        this.existingLoan = existingLoan;
    }

    public Loan getExistingLoan() {
        return existingLoan;
    }
}
