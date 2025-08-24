package com.capstone.springprojecta.services;

import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.repositories.CategoryRepository;
import com.capstone.springprojecta.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SelfProductService")
public class SelfProductService implements ProductService{
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository , CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public ResponseEntity<Product> addNewProduct(Product prod) {
        return null;
    }
}
