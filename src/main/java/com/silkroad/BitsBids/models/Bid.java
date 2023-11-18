package com.silkroad.BitsBids.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Bids")
public class Bid {
    @Id
    private Long bidId;
    private Long bidderId;
    private Long productId;
    private Long bidAmount;
        
    private LocalDateTime bidDateTime;


}
