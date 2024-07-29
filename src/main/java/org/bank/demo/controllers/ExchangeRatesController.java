package org.bank.demo.controllers;

import org.bank.demo.BL.MyCacheManager;
import org.bank.demo.BL.ExchangeRateApiService;
import org.bank.demo.beans.ConvertResult;
import org.bank.demo.beans.ExchangeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Exchange")
public class ExchangeRatesController {

    @Autowired
    MyCacheManager cacheManager;

    @Autowired
    private ExchangeRateApiService service;
    @GetMapping("/get/{from}")
    public  ResponseEntity<?> getAllCurrencies(@PathVariable String from)
    {
        ExchangeResult result = this.service.goTo3rdParty(from);
        return new ResponseEntity<>((result), HttpStatus.OK);
    }

    @GetMapping("convert")
    public ResponseEntity<?> convert(@RequestParam String from,@RequestParam String to,@RequestParam double amount){

        double rate = cacheManager.getCurrency(from, to);
        return new ResponseEntity<>(new ConvertResult(from,to,amount,rate*amount), HttpStatus.OK);
    }
}
