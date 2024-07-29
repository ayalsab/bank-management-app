package org.bank.demo.controllers;

import org.bank.demo.BL.CustomerBL;
import org.bank.demo.BL.LoanBL;
import org.bank.demo.beans.Customer;
import org.bank.demo.beans.Loan;
import org.bank.demo.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanBL loanBL;

    @GetMapping("/get/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.loanBL.getLoan(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/all")
    public List<Loan> getAllLoan()
    {
        return this.loanBL.getAllLoans();
    }

    @PostMapping("/register")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan) throws LoanAlreadyExistException, IdNumberNotValidException, InvalidCurrencyDataException{
        try {
            Loan createdLoan = this.loanBL.addLoan(loan);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
        } catch (Exception | LoanAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/get/account/{id}")
    public ResponseEntity<List<Loan>> getLoanByAccountId(@PathVariable Integer id){
        try {
            List<Loan> loans = this.loanBL.getLoanByAccountId(id);
            return ResponseEntity.ok(loans);
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
