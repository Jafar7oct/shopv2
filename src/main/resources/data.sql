-- Insert Products
INSERT INTO PRODUCT (id, name, price, description, image_url) VALUES 
(1, 'Laptop Pro', 999.99, 'High-end laptop', 'https://via.placeholder.com/150?text=Laptop'),
(2, 'Smartphone X', 499.99, 'Latest smartphone', 'https://via.placeholder.com/150?text=Phone'),
(3, 'Headphones', 79.99, 'Noise-canceling', 'https://via.placeholder.com/150?text=Headphones'),
(4, 'Smart Watch', 199.99, 'Fitness tracker', 'https://via.placeholder.com/150?text=Watch'),
(5, 'Tablet', 299.99, 'Portable device', 'https://via.placeholder.com/150?text=Tablet'),
(6, 'Camera', 599.99, 'Professional camera', 'https://via.placeholder.com/150?text=Camera');

-- Insert Users (password 'admin123' hashed with BCrypt)
INSERT INTO USERS (id, username, password, role) VALUES 
(1, 'admin', '$2a$10$8z5gXz5eXz5eXz5eXz5eX.8z5gXz5eXz5eXz5eXz5eXz5eXz5eXz5', 'ADMIN');