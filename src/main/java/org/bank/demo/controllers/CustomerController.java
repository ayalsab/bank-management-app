package org.bank.demo.controllers;

import org.bank.demo.BL.CurrencyBL;
import org.bank.demo.beans.Currency;
import org.bank.demo.exceptions.CurrencyAlreadyExistException;
import org.bank.demo.exceptions.IdNumberNotValidException;
import org.bank.demo.exceptions.InvalidCurrencyDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    private CurrencyBL currencyBL;

    @GetMapping("/get/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(this.currencyBL.getCurrency(id));
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/get/all")
    public List<Currency> getAllCurrencies()
    {
        return this.currencyBL.getAllCurrencies();
    }

    @PostMapping("/register")
    public ResponseEntity<Currency> addCurrency(@RequestBody Currency currency) throws CurrencyAlreadyExistException, IdNumberNotValidException, InvalidCurrencyDataException{
        try {
            Currency createdCurrency = this.currencyBL.addCurrency(currency);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCurrency);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
