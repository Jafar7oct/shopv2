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
        user.setId(System.currentTimeMillis()); // Simple ID generation; consider a better strategy in production
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER"); // Default role for new users
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin";
    }

    @PostMapping("/admin/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }

    @PostMapping("/admin/edit")
    public String editProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(query);
        model.addAttribute("products", products);
        return "home";
    }
}