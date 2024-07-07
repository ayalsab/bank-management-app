package org.bank.demo.beans;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "name")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account")
    private List<Loan> loans;

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Double getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
