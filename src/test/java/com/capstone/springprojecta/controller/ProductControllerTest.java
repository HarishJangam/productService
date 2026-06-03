package com.capstone.springprojecta.controller;

import com.capstone.springprojecta.exceptions.ProductNotFoundException;
import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.repositories.ProductRepository;
import com.capstone.springprojecta.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    @Autowired
    ProductRepository productRepository;

    @MockitoBean
    private ProductService productService;
    @Test
    void testProductsSameAsService(){
        List<Product> mockProd=new ArrayList<>();
        Product p1=new Product();
        p1.setTittle("iphone 15 pro max");
        Product p2=new Product();
        p2.setTittle("iphone 16 pro max");
        Product p3=new Product();
        p3.setTittle("iphone 17 pro max");
        mockProd.add(p1);
        mockProd.add(p2);
        mockProd.add(p3);
        List<Product>dublicatMockProd=new ArrayList<>();
        for(Product p : mockProd){
            Product a=new Product();
            a.setTittle(p.getTittle());
            dublicatMockProd.add(a);
        }
// step 1   `arraneg
        when(productService.getAllProducts())
                .thenReturn(ResponseEntity.ok(dublicatMockProd));
// step 2       act
        ResponseEntity<List<Product>> products=productController.getAllProducts();
        List<Product>response=products.getBody();
        assertEquals(mockProd.size(),response.size());

        for(int i=0;i<mockProd.size();i++){
            assertEquals(mockProd.get(i).getTittle(),response.get(i).getTittle());
        }
    }

//    @Test
//    void testProductNotFound() {
//
//        when(productRepository.findById(123L))
//                .thenReturn(Optional.empty());
//
//        ProductNotFoundException exception =
//                assertThrows(
//                        ProductNotFoundException.class,
//                        () -> productService.getSingleProduct(123L)
//                );
//
//        assertEquals(
//                "product with id 123 doesnt exist search for another id",
//                exception.getMessage()
//        );
//    }
    @Test
    void TestGetProductByIdSuccess(){
        Long id=1L;
        Product prod=new Product();
        prod.setTittle("best iphone ever");
        prod.setId(id);
        when(productService.getSingleProduct(any(Long.class)))
                .thenReturn(ResponseEntity.ok(prod));
        ResponseEntity<Product>response=productService.getSingleProduct(id);
        assertNotNull(response.getBody());
        assertEquals(id,response.getBody().getId());
        assertEquals("best iphone ever",response.getBody().getTittle());

    }

    @Test
    void TestGetProductByIdException(){
        Exception ex=assertThrows(ProductNotFoundException.class,()->productService.getSingleProduct(2L));
    }
}
