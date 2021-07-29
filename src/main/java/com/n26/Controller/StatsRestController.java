package com.n26.Controller;

import com.n26.Model.Transaction;
import com.n26.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by oyelakin on 5/1/2021.
 */

@RestController
public class StatsRestController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    @Validated
    public ResponseEntity<?> newTransaction(@Valid @RequestBody Transaction transaction) throws Exception{
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/statistics")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getStatistics() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getStatistics());
    }

    @DeleteMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
