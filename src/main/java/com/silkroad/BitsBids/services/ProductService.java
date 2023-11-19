package com.silkroad.BitsBids.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    
    // CREATE
    public Product registerProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    // READ
    public Product findProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->new EntityNotFoundException("invalid id")) ;
    }

    public List<Product> findAllByType(String type){
        try {
        return productRepository.findByProductType(type).get();  
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Product> getAll(){
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
        .collect(Collectors.toList());
    }
}