package org.bank.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    @JsonBackReference
    @JsonIgnore
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    @JsonBackReference
    @JsonIgnore
    private Currency currency;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "monthly_pmt")
    private float monthly_pmt;

    @Column(name = "interest_rate")
    private float interestRate;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "loan",fetch = FetchType.EAGER)
    @JsonBackReference
    @JsonIgnore
    private List<Transaction> transactions;

    public void addTransaction(Transaction transaction)
    {
        this.transactions.add(transaction);
    }

    public Loan() {
        System.out.println("Loan default CTOR");
    }

    public Loan( Account account, Currency currency, Double amount, LocalDateTime startDate, LocalDateTime endDate, float monthly_pmt, float interestRate, String status, List<Transaction> transactions) {
        this.account = account;
        this.currency = currency;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthly_pmt = monthly_pmt;
        this.interestRate = interestRate;
        this.status = status;
        this.transactions = transactions;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setMonthly_pmt(float monthly_pmt) {
        this.monthly_pmt = monthly_pmt;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public Account getAccount() {
        return account;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public float getMonthly_pmt() {
        return monthly_pmt;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public String getStatus() {
        return status;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
