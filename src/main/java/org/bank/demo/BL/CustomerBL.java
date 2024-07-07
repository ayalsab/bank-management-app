package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Customer;
import org.bank.demo.dao.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerBL {
    @Autowired
    private CustomerDAO customerDAO;



}
