package org.bank.demo.beans;

import jakarta.persistence.*;

@Entity
@Table(name = "Account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @OneToMany(mappedBy = "Transaction" + "loan") //same variable name at Student class
    private int account_id;

    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "name")
    private float balance;

   // @ManyToOne() //same variable name at Student class
    @Column(name = "currency_id")
    private int currency_id;
    @Column(name = "status")
    private String status;

}
