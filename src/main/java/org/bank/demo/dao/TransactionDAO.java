package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction,Integer> {

}
