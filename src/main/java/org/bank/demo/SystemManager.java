package org.bank.demo;

import org.bank.demo.BL.AccountBL;
import org.bank.demo.BL.CurrencyBL;
import org.bank.demo.BL.CustomerBL;
import org.bank.demo.BL.TransactionBL;
import org.bank.demo.beans.Account;
import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Customer;
import org.bank.demo.beans.Transaction;
import org.bank.demo.dao.AccountDAO;
import org.bank.demo.dao.CustomerDAO;
import org.bank.demo.exceptions.*;
import org.bank.demo.shared.AccountStatus;
import org.bank.demo.shared.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class SystemManager {

    @Autowired
    private CurrencyBL currencyBL;
    @Autowired
    private CustomerBL customerBL;
    @Autowired
    private AccountBL accountBl;
    @Autowired
    private TransactionBL transactionBL;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private CustomerDAO customerDAO;
    public void run() throws CurrencyNotFoundException {
        Currency currency =new Currency(1,"IL",1.0,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        try{
            currencyBL.addCurrency(currency);
        }catch (CurrencyAlreadyExistException e){
            currency = e.getExistingCurrency();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Customer customer = new Customer(123,"Ayal","Ayal123@gmail.com","0502569240","000","ayalsa","123456",null);
        try{
            customerBL.addCustomer(customer);
        }catch (CustomerAlreadyExistException e){
            customer = e.getExistingCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Account account = new Account(205631,customer,6.0,currency, AccountStatus.ACTIVE,null,null);
        Account account2 = new Account(205632,customer,6.0,currency, AccountStatus.ACTIVE,null,null);

        try{
            accountBl.addAccount(account);
        }catch (AccountAlreadyExistException e){
            account = e.getExistingAccount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        currencyBL.addAccountToCurrency(account,currency);

        try{
            accountBl.addAccount(account2);
        }catch (AccountAlreadyExistException e){
            account2 = e.getExistingAccount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        currencyBL.addAccountToCurrency(account2,currency);
       Account account1 = accountDAO.findAccountByUniqIdNumber(205631);
        account1.setBalance(6.0);
        try {
            accountBl.updateAccount(account1);
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }


        Transaction t = new Transaction(account,account2.getAccountId(),null,currencyBL.getCurrency(1), TransactionType.TRANSACTION,2.0, LocalDateTime.now());

        try {
            transactionBL.addTransaction(t);
            transactionBL.TransferAmount(account,accountDAO.findAccountByUniqIdNumber(205632),2.0);
            Transaction t1 = accountBl.addTransactionToAccount(t,account);
            //transactionBL.addTransaction(t1);
        } catch (TransactionAlreadyExistException | Exception e) {
            throw new RuntimeException(e);
        }


    }
}
