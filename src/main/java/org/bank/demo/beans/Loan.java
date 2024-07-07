package org.bank.demo.beans;

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
    private Integer loanId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "amount")
    private float amount;

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

    @OneToMany(mappedBy = "loan")
    private List<Transaction> transactions;

    public Loan(Integer loanId, Account account, Currency currency, float amount, LocalDateTime startDate, LocalDateTime endDate, float monthly_pmt, float interestRate, String status, List<Transaction> transactions) {
        this.loanId = loanId;
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

    public void setAmount(float amount) {
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

    public float getAmount() {
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
