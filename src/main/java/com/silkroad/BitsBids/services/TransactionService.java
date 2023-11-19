package com.silkroad.BitsBids.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.silkroad.BitsBids.models.Transaction;
import com.silkroad.BitsBids.repositories.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TransactionService {
    
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    public Transaction registerTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
    
    
    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    
    // READ
    public Transaction findTransaction(Long id){
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Transaction not found for id: " + id));
    }

    public List<Transaction> getAllTransactions(){
        return StreamSupport.stream(transactionRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
    
}
