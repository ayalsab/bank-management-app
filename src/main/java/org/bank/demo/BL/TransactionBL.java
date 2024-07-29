package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.dao.TransactionDAO;
import org.bank.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionBL {

   /* @Autowired
    private TransactionDAO transactionDAO;
    @Autowired

    private AccountBL accountBL;*/


    private final TransactionDAO transactionDAO;
    private final AccountBL accountBL;

    @Autowired
    public TransactionBL(TransactionDAO transactionDAO, @Lazy AccountBL accountBL) {
        this.transactionDAO = transactionDAO;
        this.accountBL = accountBL;
    }

    public List<Transaction> getAllTransactions(){
        return this.transactionDAO.findAll();
    }

    public Transaction addTransaction(Transaction transaction) throws TransactionAlreadyExistException, Exception {
        if(transaction !=null){

            Transaction existingTransaction = this.transactionDAO.findTransactionByTransactionId(transaction.getTransactionId());
            if(existingTransaction != null)
            {
                throw new TransactionAlreadyExistException(existingTransaction);
            }
            return this.transactionDAO.save(transaction);
        }
        throw new Exception("InvalidTransactionData");
    }


    public Transaction getTransaction(int id) throws TransactionNotFoundException {
        Optional<Transaction> transaction = this.transactionDAO.findById(id);
        if (transaction.isPresent()) {
            return transaction.get();
        }
        throw new TransactionNotFoundException();
    }

    public Transaction updateTransaction(Transaction transaction) throws TransactionNotFoundException {
        Optional<Transaction> existingTransaction = this.transactionDAO.findById(transaction.getTransactionId());
        if(existingTransaction.isEmpty())
        {
            throw new TransactionNotFoundException();
        }
        return this.transactionDAO.save(transaction);
    }

    public void TransferAmount(Account transferred,Account received,Double amount){

        try {
            if(accountBL.isActive(transferred)&&accountBL.isActive(received)){
                if(accountBL.accountBalanceEnough(transferred,amount)){
                    accountBL.changeBalance(transferred,amount*-1);
                    accountBL.changeBalance(received,amount);

                }
            }
        } catch (AccountNotActiveException | AccountBalanceNotEnough | AccountNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Transaction> getTransactionsByAccountId(Integer id) throws TransactionNotFoundException {
            List<Transaction> transactions = this.transactionDAO.findTransactionsByAccount_AccountId(id);
            if (transactions!=null) {
                return transactions;
            }
            throw new TransactionNotFoundException();

    }
}
