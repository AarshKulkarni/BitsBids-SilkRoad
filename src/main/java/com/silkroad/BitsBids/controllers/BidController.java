package com.silkroad.BitsBids.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.ResponseHandler;
import com.silkroad.BitsBids.models.Bid;
import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.services.BidService;
import com.silkroad.BitsBids.services.ProductService;

@RestController
@RequestMapping("/bids")
public class BidController {
    private final BidService bidService;
    private final ProductService productService;

    public BidController(BidService bidService, ProductService productService) {
        this.productService = productService;
        this.bidService = bidService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> registerBid(@RequestBody Bid bid) {
        Long productId = bid.getProductId();
        Bid lastBid = bidService.lastBid(productId);

        if(bid.getBidAmount() <= lastBid.getBidAmount()){
            return ResponseHandler.generateResponse("Bid amount cannot be lower than last bid", HttpStatus.BAD_REQUEST, null);
        } else if(lastBid.getBidDateTime().plusMinutes(10).isBefore(LocalDateTime.now())){
            return ResponseHandler.generateResponse("Time for Bidding has ended", HttpStatus.BAD_REQUEST, null);
        }
        bidService.registerBid(bid);
        Product biddedProduct = productService.findProduct(bid.getProductId());
        biddedProduct.setUpdatedPrice(bid.getBidAmount());
        return ResponseHandler.generateResponse("Bid successfully placed", HttpStatus.OK, bid);
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
