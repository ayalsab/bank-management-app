package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDAO extends JpaRepository<Customer,Integer> {

    @Query(value = "select * from customer where idnumber=:id", nativeQuery = true)
    Customer findCustomerByIDNumber(Integer id);

}
