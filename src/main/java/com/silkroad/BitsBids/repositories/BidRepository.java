package com.silkroad.BitsBids.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.silkroad.BitsBids.models.Bid;

@Repository
public interface BidRepository extends CrudRepository<Bid,Long> {
    Optional<Bid> findTopByProductIdOrderByBidAmountDesc(Long productId);
}