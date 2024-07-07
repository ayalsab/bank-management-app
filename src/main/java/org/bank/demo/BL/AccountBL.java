package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBL {

    @Autowired
    private AccountDAO accountDAO;

}
