package com.silkroad.BitsBids.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="bids")
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

    public Bid(Long bidderId, Long productId, Long bidAmount) {
        this.bidderId = bidderId;
        this.productId = productId;
        this.bidAmount = bidAmount;
    }
}
