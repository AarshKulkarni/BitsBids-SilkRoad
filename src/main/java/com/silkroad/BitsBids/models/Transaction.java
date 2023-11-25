package com.silkroad.BitsBids.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long amount;

    @CreationTimestamp
    private LocalDateTime transactionTime;

    public Transaction(Long buyerId, Long productId, Long sellerId, Long amount, LocalDateTime transactionTime) {
        this.buyerId = buyerId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.amount = amount;
        this.transactionTime = transactionTime;
    }
    
    
    


}

