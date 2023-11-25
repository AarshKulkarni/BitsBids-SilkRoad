package com.silkroad.BitsBids.models;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="Bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bidId;

    @Column(nullable = false)
    private Long bidderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long bidAmount;

    @Column(nullable = false)
    private LocalDateTime bidDateTime;

    @Column(nullable = false)
    private Boolean isActive;

    public Bid(Long bidderId, Long productId, Long bidAmount, LocalDateTime bidDateTime) {
        this.bidderId = bidderId;
        this.productId = productId;
        this.bidAmount = bidAmount;
        this.bidDateTime = bidDateTime;
        this.isActive = true;
    }

    
}
