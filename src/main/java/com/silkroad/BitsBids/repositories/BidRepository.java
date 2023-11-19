package com.silkroad.BitsBids.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Bid;

@Repository
public interface BidRepository extends CrudRepository<Bid,Long> {
    Bid findTopByProductIdOrderByBidAmountDesc(Long productId);
}