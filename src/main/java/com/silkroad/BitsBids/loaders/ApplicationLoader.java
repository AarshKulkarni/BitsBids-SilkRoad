package com.silkroad.BitsBids.loaders;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ApplicationLoader implements ApplicationRunner {

    private ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Load website data here
        Product[] products = new Product[10];
        products[0] = new Product();

        productRepository.save(products[0]);
    }

    
}