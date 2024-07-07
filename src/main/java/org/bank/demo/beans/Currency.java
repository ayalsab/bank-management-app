package org.bank.demo.beans;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Integer currencyId;

    @Column(name = "code")
    private String code;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @OneToMany(mappedBy = "currency")
    private List<Account> accounts;

    @OneToMany(mappedBy = "currency")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "currency")
    private List<Loan> loans;

    public Currency(Integer currencyId, String code, Double exchangeRate, List<Account> accounts, List<Transaction> transactions, List<Loan> loans) {
        this.currencyId = currencyId;
        this.code = code;
        this.exchangeRate = exchangeRate;
        this.accounts = accounts;
        this.transactions = transactions;
        this.loans = loans;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public String getCode() {
        return code;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
