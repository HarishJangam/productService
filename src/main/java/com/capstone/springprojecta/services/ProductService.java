package com.capstone.springprojecta.services;

import com.capstone.springprojecta.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
     ResponseEntity<Product>  getSingleProduct(Long id);
     ResponseEntity<List<Product>> getAllProducts();
     ResponseEntity<Product> addNewProduct(Product prod);
     ResponseEntity<Void> deleteProduct(Long id);
}
