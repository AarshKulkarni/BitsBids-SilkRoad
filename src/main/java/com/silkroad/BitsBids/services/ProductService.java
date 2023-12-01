package com.silkroad.BitsBids.services;

import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // CREATE
    public Product registerProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // READ
    public Product findProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAllByType(String type) {
        return productRepository.findByProductType(type).orElse(Collections.emptyList());
    }

    public List<Product> findProductsByUserId(Long userId) {
        return productRepository.findBySellerId(userId).orElse(Collections.emptyList());
    }

    public List<Product> getAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Boolean isExists(Long id) {
        return productRepository.existsById(id);
    }

    public List<Product> searchProducts(String query) {
        return productRepository.searchProduct(query);
    }
}
