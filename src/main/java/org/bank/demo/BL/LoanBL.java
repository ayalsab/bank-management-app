package org.bank.demo.BL;

import org.bank.demo.dao.LoanDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBL {

    @Autowired
    private LoanDAO loanDAO;

}
