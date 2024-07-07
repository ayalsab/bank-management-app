package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {

}
