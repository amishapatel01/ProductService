package dev.amisha.productservice.services;

import dev.amisha.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);
    Product createProduct(String title, String description,
                                 String image, String category, double price);


     List<Product> getAllProducts();
}
