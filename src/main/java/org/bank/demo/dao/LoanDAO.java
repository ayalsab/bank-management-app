package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanDAO extends JpaRepository<Loan,Integer> {
    Loan findLoanByLoanId(Integer id);
    //List<Loan> findLoanByAccount_AccountId(Integer id);

    @Query(value = "select * from loan where account_id=:id", nativeQuery = true)
    List<Loan> findLoanByAccount_AccountId(Integer id);
}
