package com.capstone.springprojecta.controller;
import com.capstone.springprojecta.models.Product;
import com.capstone.springprojecta.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductController productController;

    @MockitoBean
    ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProductsSuccess()throws Exception{
        Product product1=new Product();
        product1.setId(1L);
        product1.setTittle("iphone");
        Product product2=new Product();
        product2.setId(2L);
        product2.setTittle("laptop");
        List<Product> products=new ArrayList<>();
        products.add(product1);
        products.add(product2);
        when(productService.getAllProducts())
                .thenReturn(ResponseEntity.ok(products));
        mockMvc.perform(get("/products")).andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
    }
}
