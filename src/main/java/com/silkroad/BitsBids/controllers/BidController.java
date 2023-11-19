package com.silkroad.BitsBids.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.models.Bid;
import com.silkroad.BitsBids.services.BidService;

@RestController
@RequestMapping("/bids")
public class BidController {
    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<Bid> registerBid(@RequestBody Bid bid) {
        try {
            return new ResponseEntity<>(bidService.registerBid(bid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // READ
    @GetMapping("/listAll")
    public List<Bid> listBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/listById")
    public Bid listBidById(@RequestParam Long bidId) {
        return bidService.findBid(bidId);
    }
}
