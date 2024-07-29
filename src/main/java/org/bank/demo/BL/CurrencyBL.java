package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.dao.*;
import org.bank.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyBL {

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private AccountBL accountBL;

    @Autowired
    private TransactionBL transactionBL;

    @Autowired
    private LoanBL loanBL;


    public List<Currency> getAllCurrencies(){
        return this.currencyDAO.findAll();
    }

    public Currency addCurrency(Currency currency) throws CurrencyAlreadyExistException, Exception {
        if(currency !=null){


            Currency existingStudent = this.currencyDAO.findCurrencyByCurrencyId(currency.getCurrencyId());
            if(existingStudent != null)
            {
                throw new CurrencyAlreadyExistException(existingStudent);
            }
            return this.currencyDAO.save(currency);
        }
        throw new InvalidCurrencyDataException("InvalidCurrencyData");
    }

    public Currency getCurrency(int id) throws CurrencyNotFoundException {
        Optional<Currency> currency = this.currencyDAO.findById(id);
        if (currency.isPresent()) {
            return currency.get();
        }
        throw new CurrencyNotFoundException();
    }

    public Currency updateCurrency(Currency currency) throws CurrencyNotFoundException {
        Optional<Currency> existingCurrency = this.currencyDAO.findById(currency.getCurrencyId());
        if(existingCurrency.isEmpty())
        {
            throw new CurrencyNotFoundException();
        }
        return this.currencyDAO.save(currency);
    }

    public Account addAccountToCurrency(Account account, Currency currency) throws CurrencyNotFoundException {
        currency = this.getCurrency(currency.getCurrencyId());

        account.setCurrency(currency);
        currency.addAccount(account);

        this.updateCurrency(currency);
        try {
            this.accountBL.addAccount(account);
        } catch (AccountAlreadyExistException e) {
            return e.getExistingAccount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return account;
    }

    public Transaction addTransactionToCurrency(Transaction transaction, Currency currency) throws CurrencyNotFoundException {
        currency = this.getCurrency(currency.getCurrencyId());

        transaction.setCurrency(currency);
        currency.addTransaction(transaction);

        this.updateCurrency(currency);
        try {
            this.transactionBL.addTransaction(transaction);
        } catch (TransactionAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

    public Loan addLoanToCurrency(Loan loan, Currency currency) throws CurrencyNotFoundException {
        currency = this.getCurrency(currency.getCurrencyId());

        loan.setCurrency(currency);
        currency.addLoan(loan);

        this.updateCurrency(currency);
        try {
            this.loanBL.addLoan(loan);
        } catch (LoanAlreadyExistException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return loan;
    }
}
