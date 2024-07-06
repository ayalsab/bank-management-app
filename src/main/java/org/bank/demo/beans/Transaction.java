package org.bank.demo.beans;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transaction_id;

    private int account_id;
    private int second_account_id;
    private int loan_id;
    private int currency_id;
    private String type;
    private float amount;
    private Date date;

}
