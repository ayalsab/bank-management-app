package org.bank.demo.BL;

import org.bank.demo.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionBL {

    @Autowired
    private TransactionDAO transactionDAO;

}
