package com.silkroad.BitsBids.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.models.Transaction;
import com.silkroad.BitsBids.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Transaction> registerTransaction(@RequestBody Transaction transaction){
        try {
            return new ResponseEntity<>(transactionService.registerTransaction(transaction),HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteTransaction(@RequestParam Long transactionId){
        try {
            transactionService.deleteTransaction(transactionId);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Oops!We could not process the cancellation request.");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // READ
    @GetMapping("/listAll")
    public List<Transaction> listTransactions(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/listbyId")
    public Transaction listTransactionsbyId(@RequestParam Long transactionId){
        return transactionService.findTransaction(transactionId);
    }

}
