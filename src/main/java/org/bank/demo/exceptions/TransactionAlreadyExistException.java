package org.bank.demo.exceptions;

import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;

public class TransactionAlreadyExistException extends Throwable {
    private Transaction existingTransaction;
    public TransactionAlreadyExistException(Transaction existingTransaction) {
        this.existingTransaction = existingTransaction;
    }

    public Transaction getExistingTransaction() {
        return existingTransaction;
    }
}
