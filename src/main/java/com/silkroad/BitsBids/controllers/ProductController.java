package com.silkroad.BitsBids.controllers;

import java.util.List;

import com.silkroad.BitsBids.models.Bid;
import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.ResponseHandler;
import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.services.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final AuthService authService;

    public ProductController(ProductService productService, AuthService authService) {
        this.productService = productService;
        this.authService = authService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> registerProduct(@RequestBody Product product) {
        try {
            Product registerProduct = productService.registerProduct(product);
            return ResponseHandler.generateResponse("",HttpStatus.OK,registerProduct);

        } catch (Exception e) {
            return ResponseHandler.generateResponse("An error occurred",HttpStatus.OK,null);
        }
    }

    // DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseHandler.generateResponse("Successfully deleted",HttpStatus.OK,true);

        } catch (Exception e) {
            System.out.println(productId + " Could not be deleted");
            return ResponseHandler.generateResponse("Error deleting product",HttpStatus.BAD_REQUEST,false);
        }
    }

    // READ
    @GetMapping("/listAll")
    public ResponseEntity<?> listProducts() {
        List<Product> products = productService.getAll();
        return ResponseHandler.generateResponse("",HttpStatus.OK,products);
    }

    @GetMapping("/listbyId")
    public ResponseEntity<?> listProductsbyId(@RequestParam Long productId) {
        Product product = productService.findProduct(productId);
        return ResponseHandler.generateResponse("",HttpStatus.OK,product);
    }

    @GetMapping("/listbyCategory")
    public ResponseEntity<?> listProductsbyCategory(@RequestParam String category) {
        List<Product> productList = productService.findAllByType(category);
        return ResponseHandler.generateResponse("",HttpStatus.OK,productList);
    }

    @GetMapping("/listbyUserId")
    public ResponseEntity<?> listbyUserId(@RequestParam Long userId) {
        List<Product> products = productService.findProductsByUserId(userId);
        return ResponseHandler.generateResponse("",HttpStatus.OK,products);
    }

    public ResponseEntity<?> searchProducts(@RequestParam("query") String query) {
        List<Product> searchedProds = productService.searchProducts(query);
        return ResponseHandler.generateResponse("", HttpStatus.OK, searchedProds);
    }

    @PostMapping("/unbid")
    public ResponseEntity<?> unbidProduct(@RequestParam Long prodId) {
        Product foundProd = productService.findProduct(prodId);
        if(foundProd == null){
            return ResponseHandler.generateResponse("Product doesn't exist", HttpStatus.NOT_FOUND, "");
        }
        else if(foundProd.getPreviousBids().isEmpty()){
            return ResponseHandler.generateResponse("There are no bids on this product",HttpStatus.BAD_REQUEST,"");
        }
        else {
            foundProd.setIsBidable(false);
//            Deduction of money for buyer
            Bid lastBid = foundProd.getPreviousBids().get(foundProd.getPreviousBids().size() - 1);
            Long deductedAmount = foundProd.getPreviousBids().get(foundProd.getPreviousBids().size() - 1).getBidAmount();
            User buyer = authService.getUser(lastBid.getBidderId()).get();
            User seller = authService.getUser(foundProd.getSellerId()).get();

            seller.setMoney(seller.getMoney() + deductedAmount);
            buyer.setMoney(buyer.getMoney() + deductedAmount);

            authService.saveUser(seller);
            authService.saveUser(buyer);

            productService.registerProduct(foundProd);
            return ResponseHandler.generateResponse("Successfully unbid product", HttpStatus.OK, foundProd);
        }
    }
}
