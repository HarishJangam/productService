package com.capstone.springprojecta.services;

import com.capstone.springprojecta.models.Category;
import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.repositories.CategoryRepository;
import com.capstone.springprojecta.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Product> getSingleProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products=productRepository.findAll();
        if(!products.isEmpty()){
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Product> addNewProduct(Product prod) {
        Optional<Category> category=categoryRepository.findById(prod.getCategory().getId());
        if(category.isPresent()){
            prod.setCategory(category.get());
            Product savedProduct=productRepository.save(prod);
            return ResponseEntity.status(201).body(savedProduct);
        }
        else{
            Category cat=new Category();
            cat.setName(prod.getCategory().getName());
            categoryRepository.save(cat);
            prod.setCategory(categoryRepository.findByName(cat.getName()).get());
            Product savedProduct=productRepository.save(prod);
            return ResponseEntity.status(201).body(savedProduct);
        }

//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        Optional<Product> product=productRepository.findById(id);
        if(product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
