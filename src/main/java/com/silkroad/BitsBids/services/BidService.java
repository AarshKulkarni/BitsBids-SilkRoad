package com.silkroad.BitsBids.services;

import java.time.LocalDateTime;
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
        Long productId=bid.getProductId();

        Bid lastBid=bidRepository.findTopByProductIdOrderByBidAmountDesc(productId);
        if(lastBid==null)
            return bidRepository.save(bid);

        
        if(lastBid!=null&&lastBid.getBidAmount()>=bid.getBidAmount())
            throw new RuntimeException("New bid price must be higher than the current bid price");


        LocalDateTime currentDateTime=LocalDateTime.now();
        if(currentDateTime.isAfter(bid.getBidDateTime()))
            throw new RuntimeException("Oops! Deadline has already elapsed!!");

        return bidRepository.save(bid);
    }

    // READ
    public Bid findBid(Long id){
        return bidRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Oops! Something went wrong")) ;
    }

    public List<Bid> getAllBids(){
        return StreamSupport.stream(bidRepository.findAll().spliterator(),false).collect(Collectors.toList());
    }
}


