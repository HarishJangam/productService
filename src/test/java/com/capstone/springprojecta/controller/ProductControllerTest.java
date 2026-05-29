package com.capstone.springprojecta.controller;

import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

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
}
