package org.bank.demo.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "IDNumber")
    private Integer IDNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    @JsonIgnore
    private List<Account> accounts;

    public void addAccount(Account account)
    {
        this.accounts.add(account);
    }

    public Customer() {
        System.out.println("Customer default CTOR");
    }

    public Customer(Integer id,String name, String email, String phone, String address, String username, String password, List<Account> accounts) {
        this.IDNumber = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Integer getIDNumber() {
        return IDNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setIDNumber(Integer id) {
        this.IDNumber = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
