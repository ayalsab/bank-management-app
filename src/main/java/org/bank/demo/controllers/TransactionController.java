package org.bank.demo.controllers;

import org.bank.demo.BL.LoanBL;
import org.bank.demo.BL.TransactionBL;
import org.bank.demo.beans.Loan;
import org.bank.demo.beans.Transaction;
import org.bank.demo.exceptions.IdNumberNotValidException;
import org.bank.demo.exceptions.InvalidCurrencyDataException;
import org.bank.demo.exceptions.LoanAlreadyExistException;
import org.bank.demo.exceptions.TransactionAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    @Autowired
    private TransactionBL transactionBL;

    @GetMapping("/get/{id}")
    public ResponseEntity<Transaction> getLoan(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.transactionBL.getTransaction(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/all")
    public List<Transaction> getAllLoan()
    {
        return this.transactionBL.getAllTransactions();
    }

    @PostMapping("/register")
    public ResponseEntity<Transaction> addLoan(@RequestBody Transaction transaction) throws TransactionAlreadyExistException, IdNumberNotValidException, InvalidCurrencyDataException{
        try {
            Transaction createdLoan = this.transactionBL.addTransaction(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
        } catch (Exception | TransactionAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get/account/{id}")
    public ResponseEntity<List<Transaction>>  getaccounts(@PathVariable Integer id){
        try {
            List<Transaction> transactions = this.transactionBL.getTransactionsByAccountId(id);
            return ResponseEntity.ok(transactions);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
