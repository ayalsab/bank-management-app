package org.bank.demo.controllers;

import org.bank.demo.BL.CurrencyBL;
import org.bank.demo.BL.CustomerBL;
import org.bank.demo.beans.Currency;
import org.bank.demo.beans.Customer;
import org.bank.demo.exceptions.CurrencyAlreadyExistException;
import org.bank.demo.exceptions.CustomerAlreadyExistException;
import org.bank.demo.exceptions.IdNumberNotValidException;
import org.bank.demo.exceptions.InvalidCurrencyDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    private CustomerBL customerBL;

    @GetMapping("/get/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.customerBL.getCustomer(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/all")
    public List<Customer> getAllCustomers()
    {
        return this.customerBL.getAllCustomers();
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CurrencyAlreadyExistException, IdNumberNotValidException, InvalidCurrencyDataException{
        try {
            Customer createdCustomer = this.customerBL.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } catch (Exception | CustomerAlreadyExistException e) {
            throw new RuntimeException(e);
        }
    }
}
