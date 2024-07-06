package org.bank.demo.beans;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loan_id;

    @Column(name = "account_id")
    private int account_id;

    @Column(name = "currency_id")
    private int currency_id;

    @Column(name = "amount")
    private float amount;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "monthly_pmt")
    private float monthly_pmt;

    @Column(name = "interest_rate")
    private float interest_rate;

    @Column(name = "password")
    private String status;
}
