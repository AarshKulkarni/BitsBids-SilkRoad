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
        Product bidedProduct = productService.findProduct(bid.getProductId());
        if (bidedProduct.getIsBidable()) {
            if (bidedProduct.getPreviousBids().isEmpty() && bidedProduct.getInitialPrice() < bid.getBidAmount()) {
                List<Bid> biddedList = bidedProduct.getPreviousBids();
                biddedList.add(bid);
                bidedProduct.setPreviousBids(biddedList);
                productService.registerProduct(bidedProduct);
                return ResponseHandler.generateResponse("Bid Sucessfully Placed", HttpStatus.OK, bidedProduct);
            } else if (bidedProduct.getPreviousBids().isEmpty()
                    && bidedProduct.getInitialPrice() > bid.getBidAmount()) {
                return ResponseHandler.generateResponse("Enter an appropriate amount", HttpStatus.BAD_REQUEST,
                        bidedProduct);
            } else {
                Bid lastBid = bidedProduct.getPreviousBids().get(bidedProduct.getPreviousBids().size() - 1);
                if (lastBid.getBidAmount() < bid.getBidAmount()) {
                    List<Bid> biddedList = bidedProduct.getPreviousBids();
                    biddedList.add(bid);
                    bidedProduct.setPreviousBids(biddedList);
                    productService.registerProduct(bidedProduct);
                    return ResponseHandler.generateResponse("Bid Sucessfully Placed", HttpStatus.OK, bidedProduct);
                } else {
                    return ResponseHandler.generateResponse("Bid Amount is Lower than pervious Bid",
                            HttpStatus.BAD_REQUEST, null);
                }
            }
        } else {
            return ResponseHandler.generateResponse("Cant Place Bid Anymore", HttpStatus.BAD_REQUEST, null);

        }
    }

    // READ
    @GetMapping("/listAll")
    public List<Bid> listBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/listById")
    public ResponseEntity<?> listBidById(@RequestParam Long bidId) {
        if (bidService.findBid(bidId).isEmpty()) {
            return ResponseHandler.generateResponse("No such bid exists", HttpStatus.OK, null);
        } else {
            Bid requiredBid = bidService.findBid(bidId).get();
            return ResponseHandler.generateResponse("", HttpStatus.OK, requiredBid);
        }
    }
}
