package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionDAO extends JpaRepository<Transaction,Integer> {
    Transaction findTransactionByTransactionId(Integer id);

    @Query(value = "select * from Transaction where account_id=:id", nativeQuery = true)
    List<Transaction> findTransactionsByAccount_AccountId(Integer id);
}
