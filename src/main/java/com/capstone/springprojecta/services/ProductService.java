package com.capstone.springprojecta.services;

import com.capstone.springprojecta.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
     Product  getSingleProduct(Long id);
     List<Product> getAllProducts();
     ResponseEntity<Product> addNewProduct(Product prod);
}
