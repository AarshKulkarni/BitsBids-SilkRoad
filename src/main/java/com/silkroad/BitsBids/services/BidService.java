package com.silkroad.BitsBids.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.silkroad.BitsBids.models.Bid;
import com.silkroad.BitsBids.repositories.BidRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BidService {
    private BidRepository bidRepository;

    public BidService(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }
    
    // CREATE
    public Bid registerBid(Bid bid){
        return bidRepository.save(bid);
    }

    // READ
    public Bid findBid(Long id){
        return bidRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Oops! Something went wrong")) ;
    }

    public List<Bid> getAllBids(){
        return StreamSupport.stream(bidRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }

    public Bid lastBid(Long productId){
        return bidRepository.findTopByProductIdOrderByBidAmountDesc(productId);
    }
}


