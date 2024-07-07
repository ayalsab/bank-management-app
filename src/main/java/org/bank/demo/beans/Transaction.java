package org.bank.demo.beans;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "second_account_id")
    private Integer secondAccountId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "type")
    private String type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDateTime date;

    public Transaction(Integer transactionId, Account account, Integer secondAccountId, Loan loan, Currency currency, String type, Double amount, LocalDateTime date) {
        this.transactionId = transactionId;
        this.account = account;
        this.secondAccountId = secondAccountId;
        this.loan = loan;
        this.currency = currency;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setSecondAccountId(Integer secondAccountId) {
        this.secondAccountId = secondAccountId;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public Integer getSecondAccountId() {
        return secondAccountId;
    }

    public Loan getLoan() {
        return loan;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
