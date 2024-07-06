package org.bank.demo.beans;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "Currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int currency_id;

    @Column(name = "code")
    private String code;

    @Column(name = "exchange_rate")
    private float exchange_rate;
}
