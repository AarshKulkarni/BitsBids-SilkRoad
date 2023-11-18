package com.silkroad.BitsBids.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE 
    @PostMapping("/create")
    public ResponseEntity<Product> registerProduct(@RequestBody Product product){
        try {
            return new ResponseEntity<>(productService.registerProduct(product),HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteProduct(@RequestParam Long productId){
        try {
            productService.deleteProduct(productId);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(productId + " Could not be deleted");
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // READ
    @GetMapping("/listAll")
    public List<Product> listProducts(){
        return productService.getAll();
    }

    @GetMapping("/listbyId")
    public Product listProductsbyId(@RequestParam Long productId){
        return productService.findProduct(productId);
    }

    @GetMapping("/listbyCategory")
    public List<Product> listProductsbyCategory(@RequestParam String category){
        return productService.findAllByType(category);
    }
}
