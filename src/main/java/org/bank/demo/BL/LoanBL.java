package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Customer;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.dao.LoanDAO;
import org.bank.demo.dao.TransactionDAO;
import org.bank.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanBL {

 /*   @Autowired
    private LoanDAO loanDAO;
    @Autowired
    private TransactionBL transactionBL;

*/
    private final LoanDAO loanDAO;
    private final TransactionBL transactionBL;

    @Autowired
    public LoanBL(LoanDAO loanDAO, @Lazy TransactionBL transactionBL) {
        this.loanDAO = loanDAO;
        this.transactionBL = transactionBL;
    }


    public List<Loan> getAllLoans(){
        return this.loanDAO.findAll();
    }

    public Loan addLoan(Loan loan) throws LoanAlreadyExistException, Exception {
        if(loan !=null){


            Loan existingLoan = this.loanDAO.findLoanByLoanId(loan.getLoanId());
            if(existingLoan != null)
            {
                throw new LoanAlreadyExistException(existingLoan);
            }
            return this.loanDAO.save(loan);
        }
        throw new Exception("InvalidLoanData");
    }


    public Loan getLoan(int id) throws LoanNotFoundException {
        Optional<Loan> loan = this.loanDAO.findById(id);
        if (loan.isPresent()) {
            return loan.get();
        }
        throw new LoanNotFoundException();
    }

    public List<Loan> getLoanByAccountId(int id) throws LoanNotFoundException {
        List<Loan> loan = this.loanDAO.findLoanByAccount_AccountId(id);
        if (loan!=null) {
            return loan;
        }
        throw new LoanNotFoundException();
    }

    public Loan updateLoan(Loan loan) throws LoanNotFoundException {
        Optional<Loan> existingLoan = this.loanDAO.findById(loan.getLoanId());
        if(existingLoan.isEmpty())
        {
            throw new LoanNotFoundException();
        }
        return this.loanDAO.save(loan);
    }

    public Transaction addTransactionToLoan(Transaction transaction, Loan loan) throws LoanNotFoundException {
        loan = this.getLoan(loan.getLoanId());

        transaction.setLoan(loan);
        loan.addTransaction(transaction);

        this.updateLoan(loan);
        try {
            this.transactionBL.addTransaction(transaction);
        } catch (TransactionAlreadyExistException | Exception e) {
            throw new RuntimeException(e);
        }

        return transaction;
    }

}
