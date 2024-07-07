package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.bank.demo.dao.CurrencyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyBL {

    @Autowired
    private CurrencyDAO currencyDAO;

}
