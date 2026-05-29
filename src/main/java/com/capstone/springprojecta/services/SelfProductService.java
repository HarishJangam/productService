package com.capstone.springprojecta.services;

import com.capstone.springprojecta.exceptions.ProductNotFoundException;
import com.capstone.springprojecta.models.Category;
import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.repositories.CategoryRepository;
import com.capstone.springprojecta.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
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
    public ResponseEntity<Product> getSingleProduct(Long id)  {
//        Optional<Product> product=productRepository.findById(id);
//        if(product.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + id));

        return ResponseEntity.ok(product);
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
        String catName = prod.getCategory().getName();
        Optional<Category> category=categoryRepository.findByName(catName);
        if(category != null && !category.isEmpty()){
            prod.setCategory(category.get());
            Product savedProduct=productRepository.save(prod);
            return ResponseEntity.status(201).body(savedProduct);
        }
        else{
            Category cat=new Category();
            cat.setName(catName);
            prod.setCategory( categoryRepository.save(cat));
            Product savedProduct=productRepository.save(prod);
            return ResponseEntity.status(201).body(savedProduct);
        }
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

    @Override
    public Product updateProduct(Long id , Product newProduct) {
        Product oldProduct = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));
        oldProduct.setTittle(newProduct.getTittle());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setImageUrl(newProduct.getImageUrl());
        if (newProduct.getCategory() != null) {
            oldProduct.setCategory(newProduct.getCategory());
        }
//        return productRepository.save(oldProduct);
        return productRepository.save(oldProduct);

    }


}
