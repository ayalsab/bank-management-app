package org.bank.demo.BL;

import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.dao.AccountDAO;
import org.bank.demo.exceptions.*;
import org.bank.demo.shared.AccountStatus;
import org.bank.demo.shared.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountBL {

/*
    @Autowired
    private AccountDAO accountDAO;
    private TransactionBL transactionBL;
    @Autowired
    private LoanBL loanBL;
    @Autowired
    EmailServiceBL emailServiceBL;
*/

    private final AccountDAO accountDAO;
    private final TransactionBL transactionBL;
    private final LoanBL loanBL;
    private final EmailServiceBL emailServiceBL;
    private final CustomerBL customerBL;
    @Autowired
    public AccountBL(AccountDAO accountDAO, @Lazy TransactionBL transactionBL, LoanBL loanBL, EmailServiceBL emailServiceBL,@Lazy CustomerBL customerBL) {
        this.accountDAO = accountDAO;
        this.transactionBL = transactionBL;
        this.loanBL = loanBL;
        this.emailServiceBL = emailServiceBL;
        this.customerBL = customerBL;
    }


    public Account addAccount(Account account) throws AccountAlreadyExistException, Exception {
        if(account !=null){

            if(account.getUniqIdNumber() == null){
                throw new IdNumberNotValidException();
            }
            Account existingAccount = this.accountDAO.findAccountByUniqIdNumber(account.getUniqIdNumber());
            if(existingAccount != null)
            {
                throw new AccountAlreadyExistException(existingAccount);
            }
            return this.accountDAO.save(account);
        }
        throw new Exception("InvalidAccountData");
    }

    public Account getAccount(int id) throws AccountNotFoundException {
        Optional<Account> account = this.accountDAO.findById(id);
        if (account.isPresent()) {
            return account.get();
        }
        throw new AccountNotFoundException();
    }

    public Account updateAccount(Account account) throws AccountNotFoundException {
        Optional<Account> existingAccount = this.accountDAO.findById(account.getAccountId());
        if(existingAccount.isEmpty())
        {
            throw new AccountNotFoundException();
        }
        return this.accountDAO.save(account);
    }

    public List<Account> getAllAccounts(){
        return this.accountDAO.findAll();
    }

    public Transaction addTransactionToAccount(Transaction transaction, Account account) throws Exception, TransactionAlreadyExistException {
        account = this.getAccount(account.getAccountId());

        transaction.setAccount(account);
        account.addTransaction(transaction);
        this.updateAccount(account);
        //this.transactionBL.addTransaction(transaction);

        return transaction;
    }

    public Transaction addTransactionToAccount(Transaction transaction, Integer transferAccountID) throws Exception, TransactionAlreadyExistException {
        Account transferAccount = this.getAccount(transferAccountID);
        Account receivableAccount = this.getAccount((transaction.getSecondAccountId()));

        transaction.setCurrency(transferAccount.getCurrency());
        transaction.setAccount(transferAccount);
        transaction.setType(TransactionType.TRANSACTION);

        transactionBL.TransferAmount(transferAccount,receivableAccount,transaction.getAmount());

        transaction = transactionBL.addTransaction(transaction);
        emailServiceBL.TransactionConformation(transferAccount,transaction.getAmount(),transaction);

        return transaction;
    }

    public Double changeBalance(Account account, Double amount) throws AccountNotFoundException, AccountBalanceNotEnough {
        if(account.getBalance()+amount<0){
            throw new AccountBalanceNotEnough();
        }
        account.setBalance(account.getBalance()+amount);
        updateAccount(account);
        return amount;
    }

    public Loan addLoanToAccount(Loan loan, Integer accountId) throws Exception, LoanAlreadyExistException, TransactionAlreadyExistException {
        Account account = this.getAccount(accountId);
        loan.setCurrency(account.getCurrency());
        loan.setAccount(account);
        account.addLoan(loan);

        loan = this.loanBL.addLoan(loan);

        Transaction transaction = new Transaction(account,null,loan,account.getCurrency(),TransactionType.LOAN, loan.getAmount(), LocalDateTime.now());
        transactionBL.addTransaction(transaction);


        changeBalance(account,loan.getAmount());
        this.updateAccount(account);

        emailServiceBL.loanEmailConformation(loan,account);

        return loan;
    }

    public boolean isActive(Account account) throws AccountNotActiveException {
        if(account.getStatus().equals(AccountStatus.ACTIVE)){
            return account.getStatus().equals(AccountStatus.ACTIVE);
        }
        throw new AccountNotActiveException();
    }

    public boolean accountBalanceEnough(Account account,Double amount) throws AccountBalanceNotEnough {
        if(account.getBalance()-amount>0){
            return account.getBalance()+amount>0;
        }
        throw new AccountBalanceNotEnough();
    }

    public Account updateStatus(Integer accountId, AccountStatus status) throws AccountNotFoundException {
        Account account = getAccount(accountId);
        account.setStatus(status);
        this.updateAccount(account);
        return account;
    }

    public Double getAccountBalance(Integer id) throws AccountNotFoundException {
        Account account = getAccount(id);
        return account.getBalance();
    }

    public Double deposit(Integer id, Double amount) throws AccountNotFoundException, AccountBalanceNotEnough {
        Account account = getAccount(id);
        try {
            if(this.isActive(account)){
                changeBalance(account,amount);

                Transaction transaction = new Transaction(account,null,null,account.getCurrency(),amount>0?TransactionType.DEPOSIT:TransactionType.WITHDRAWAL, amount, LocalDateTime.now());
                transactionBL.addTransaction(transaction);

                emailServiceBL.DepositEmail(account,amount);

                return account.getBalance();
            }
        } catch (TransactionAlreadyExistException | Exception e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    public Account register(Account account, Integer id) throws Exception {
        try {
            account = this.addAccount(account);
        } catch (AccountAlreadyExistException e) {
            account = e.getExistingAccount();
        }
        customerBL.addAccountToCustomer(account,customerBL.getCustomer(id));
        return account;
    }


    /*public ResponseEntity<Loan> getLoansByAccountId(Integer id) throws AccountNotFoundException {
        Account account = getAccount(id);
        return (ResponseEntity<Loan>) account.getLoans();

    }*/
}


