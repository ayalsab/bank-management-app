package org.bank.demo.controllers;

import org.bank.demo.BL.AccountBL;
import org.bank.demo.beans.Account;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.exceptions.*;
import org.bank.demo.shared.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountBL accountBL;


    @GetMapping("/get/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.accountBL.getAccount(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/all")
    public List<Account> getAllAccounts()
    {
        return this.accountBL.getAllAccounts();
    }

    @PostMapping("/register/{id}")
    public ResponseEntity<?> addAccount(@RequestBody Account account,@PathVariable Integer id) throws AccountAlreadyExistException, IdNumberNotValidException{
        try {
            account = this.accountBL.register(account,id);

            return ResponseEntity.ok(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/status/{id}")
    public Account updateStatus(@RequestBody AccountStatus status, @PathVariable Integer id) throws AccountAlreadyExistException, IdNumberNotValidException {
        try {
            return this.accountBL.updateStatus(id, status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/balance/{id}")
    public ResponseEntity<Double> getbalance(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.accountBL.getAccountBalance(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PutMapping("/deposit/{id}/{amount}")
    public ResponseEntity<Double> Deposit(@PathVariable Integer id, @PathVariable Double amount) throws AccountAlreadyExistException, IdNumberNotValidException {
        try {
            Double updatedBalance = accountBL.deposit(id, amount);
            return ResponseEntity.ok(updatedBalance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/withdrawal/{id}/{amount}")
    public ResponseEntity<Double> Withdrawal(@PathVariable Integer id, @PathVariable Double amount) throws AccountAlreadyExistException, IdNumberNotValidException {
        try {
            Double updatedBalance = accountBL.deposit(id, amount*-1);
            return ResponseEntity.ok(updatedBalance);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/register/loan/{id}")
    public Loan addLoan(@RequestBody Loan loan, @PathVariable Integer id) throws AccountAlreadyExistException, IdNumberNotValidException{
        try {
            return this.accountBL.addLoanToAccount(loan,id);
        } catch (Exception | LoanAlreadyExistException | TransactionAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("transaction/{id}")
    public Transaction addTransaction(@RequestBody Transaction transaction, @PathVariable Integer id) throws AccountAlreadyExistException, IdNumberNotValidException{
        try {
            return this.accountBL.addTransactionToAccount(transaction,id);
        } catch (Exception  | TransactionAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }



}
