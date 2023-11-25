package com.silkroad.BitsBids.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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
    
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
    @Column(nullable = false)
    private LocalDate transactionDate;
}
