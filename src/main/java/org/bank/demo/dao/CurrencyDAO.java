package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDAO extends JpaRepository<Currency,Integer> {

}
