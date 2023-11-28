package com.silkroad.BitsBids.loaders;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.silkroad.BitsBids.models.Product;
import com.silkroad.BitsBids.models.User;
import com.silkroad.BitsBids.repositories.ProductRepository;
import com.silkroad.BitsBids.repositories.UserRepository;

import lombok.AllArgsConstructor;

// @Component
@AllArgsConstructor
public class ApplicationLoader implements ApplicationRunner {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userRepository.save(
                new User("Aarsh Kulkarni", "f20220184@hyderabad.bits-pilani.ac.in", "ganja-ganja", "040-14991411"));

        // Load website data here
        Product[] products = new Product[10];
        products[0] = new Product(1L, "Iphone 10", "An Iphone 10", 10000L, "Electronics",
                "https://imgs.search.brave.com/RL66zVUtrMwAE2GMsjo0vRTOtDSD8LMAj65nYwuAXDI/rs:fit:560:320:1/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy90/aHVtYi8zLzMyL0lQ/aG9uZV9YX3ZlY3Rv/ci5zdmcvNTEycHgt/SVBob25lX1hfdmVj/dG9yLnN2Zy5wbmc");
        productRepository.save(products[0]);
    }

}