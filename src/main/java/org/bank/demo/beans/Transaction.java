package org.bank.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.bank.demo.shared.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    @JsonBackReference
    @JsonIgnore
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    @JsonIgnore
    private Account account;

    @Column(name = "second_account_id")
    private Integer secondAccountId;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    @JsonBackReference
    @JsonIgnore
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonBackReference
    @JsonIgnore
    private Currency currency;

    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDateTime date;

    public Transaction() {
        System.out.println("Transaction default CTOR");
    }

    public Transaction(Account account, Integer secondAccountId, Loan loan, Currency currency, TransactionType type, Double amount, LocalDateTime date) {
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

    public void setType(TransactionType type) {
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

    public TransactionType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
