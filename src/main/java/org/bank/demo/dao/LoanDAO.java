package org.bank.demo.dao;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanDAO extends JpaRepository<Loan,Integer> {

}
