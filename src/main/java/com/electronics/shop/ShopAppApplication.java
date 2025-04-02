package com.electronics.shop;

import com.electronics.shop.model.Product;
import com.electronics.shop.model.User;
import com.electronics.shop.repository.ProductRepository;
import com.electronics.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ShopAppApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ShopAppApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ProductRepository productRepository, UserRepository userRepository) {
        return args -> {
            productRepository.save(new Product(1L, "Laptop Pro", 999.99, "High-end laptop", "https://ssl-product-images.www8-hp.com/digmedialib/prodimg/lowres/c08484441.png"));
            productRepository.save(new Product(2L, "Smartphone X", 499.99, "Latest smartphone", "https://www.phoneplacekenya.com/wp-content/uploads/2023/01/iPhone-15.jpg"));
            productRepository.save(new Product(3L, "Headphones", 79.99, "Noise-canceling", "https://cdn.thewirecutter.com/wp-content/media/2023/07/bluetoothheadphones-2048px-0876.jpg"));
            productRepository.save(new Product(4L, "Smart Watch", 199.99, "Fitness tracker", "https://leaders.jo/wp-content/uploads/2023/09/%D8%A7%D8%A8%D9%84-%D9%88%D9%88%D8%AA%D8%B4-%D9%8A%D8%B1%D9%8A%D8%B29-%D8%A8%D8%A7%D9%86%D8%AF-%D8%B3%D8%A8%D9%88%D8%B1%D8%AA-%D8%B2%D9%87%D8%B1%D9%8A.png"));
            productRepository.save(new Product(5L, "Tablet", 299.99, "Portable device", "https://cdn.thewirecutter.com/wp-content/media/2024/06/besttablets-2048px-9875.jpg"));
            productRepository.save(new Product(6L, "Camera", 599.99, "Professional camera", "https://i5.walmartimages.com/seo/Canon-EOS-90D-DSLR-Camera-with-18-135mm-Lens-3616C016_d5438c50-f566-42e6-968b-ea49b53e1b1f_1.d37dc514f4f3f657db267af16621a2ae.jpeg"));

            User admin = new User();
            admin.setId(1L);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
        };
    }
}