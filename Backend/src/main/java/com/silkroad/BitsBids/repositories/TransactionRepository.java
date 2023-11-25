package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Long>{
    
}
