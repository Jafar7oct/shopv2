package com.electronics.shop;

import com.electronics.shop.model.Product;
import com.electronics.shop.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopAppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository) {
        return args -> {
            productRepository.save(new Product(1L, "Laptop Pro", 999.99, "High-end laptop", "https://via.placeholder.com/150?text=Laptop"));
            productRepository.save(new Product(2L, "Smartphone X", 499.99, "Latest smartphone", "https://via.placeholder.com/150?text=Phone"));
            productRepository.save(new Product(3L, "Headphones", 79.99, "Noise-canceling", "https://via.placeholder.com/150?text=Headphones"));
            productRepository.save(new Product(4L, "Smart Watch", 199.99, "Fitness tracker", "https://via.placeholder.com/150?text=Watch"));
            productRepository.save(new Product(5L, "Tablet", 299.99, "Portable device", "https://via.placeholder.com/150?text=Tablet"));
            productRepository.save(new Product(6L, "Camera", 599.99, "Professional camera", "https://via.placeholder.com/150?text=Camera"));
        };
    }
}