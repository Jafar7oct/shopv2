package com.electronics.shop.controller;

import com.electronics.shop.model.Product;
import com.electronics.shop.model.User;
import com.electronics.shop.repository.ProductRepository;
import com.electronics.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setId(System.currentTimeMillis());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/admin/manage")
    public String manageProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "manage";
    }

    @PostMapping("/admin/add")
    public String addProduct(@RequestParam Long id, @RequestParam String name, @RequestParam double price, 
                            @RequestParam String description, @RequestParam String imageUrl) {
        Product product = new Product(id, name, price, description, imageUrl);
        productRepository.save(product);
        return "redirect:/admin/manage";
    }

    @PostMapping("/admin/edit")
    public String editProduct(@RequestParam Long id, @RequestParam String name, @RequestParam double price) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
        return "redirect:/admin/manage";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/manage";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        model.addAttribute("products", products);
        return "home";
    }
}