package org.bank.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import org.bank.demo.shared.AccountStatus;



@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonBackReference
    @JsonIgnore
    private Integer accountId;

    @Column(name = "IDNumber")
    private Integer uniqIdNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonBackReference
    @JsonIgnore
    private Currency currency;

    @Column(name = "status")
    private AccountStatus status;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "account",fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    private List<Loan> loans;

    public void addTransaction(Transaction transaction)
    {
        this.transactions.add(transaction);
    }

    public void addLoan(Loan loan)
    {
        this.loans.add(loan);
    }

    public Account() {
        System.out.println("Account default CTOR");
    }

    public Account(Integer idNumber, Customer customer, Double balance, Currency currency, AccountStatus status, List<Transaction> transactions, List<Loan> loans) {
        this.uniqIdNumber = idNumber;
        this.customer = customer;
        this.balance = balance;
        this.currency = currency;
        this.status = status;
        this.transactions = transactions;
        this.loans = loans;
    }

    public Integer getUniqIdNumber() {
        return uniqIdNumber;
    }

    public void setUniqIdNumber(Integer uniqIdNumber) {
        this.uniqIdNumber = uniqIdNumber;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setStatus(AccountStatus status) {
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

    public AccountStatus getStatus() {
        return status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
