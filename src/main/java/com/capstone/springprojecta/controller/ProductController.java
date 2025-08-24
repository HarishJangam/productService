package com.capstone.springprojecta.controller;

import com.capstone.springprojecta.commons.AuthenticationCommons;
import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService,
                             AuthenticationCommons authenticationCommons) {
        this.authenticationCommons=authenticationCommons;
        this.productService = productService;
    }
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(@RequestHeader("AuthenticationToken") String token) {
            if(authenticationCommons.validateToken(token)==null){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
//        return null;
    }

    @GetMapping("/{id}") // this id and  PathVariable id names should be if parameter different also fine.
    public  Product getSingleProduct(@PathVariable("id") Long idValue) {
        return productService.getSingleProduct(idValue);
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody Product newProduct) {
        return productService.addNewProduct(newProduct);
//        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id ,@RequestBody Product newProduct) {
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@RequestBody Product newProduct, @PathVariable("id") Long id) {
        return new Product();
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {

    }
}
