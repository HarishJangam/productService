package com.capstone.springprojecta.services;

import com.capstone.springprojecta.dto.FakeStroreProductServiceDto;
import com.capstone.springprojecta.dto.FullApiResonse;
import com.capstone.springprojecta.dto.GetAllProductDto;
import com.capstone.springprojecta.models.Category;
import com.capstone.springprojecta.models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service("FakestoreProductService")
public class FakestoreProductServiceImp implements ProductService {

    RestTemplate restTemplate;

    @Autowired
    public FakestoreProductServiceImp(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private ResponseEntity<Product> fakeStoreToProduct(FakeStroreProductServiceDto productDto ){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setTittle(productDto.getTitle());
        product.setImageUrl(productDto.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(productDto.getCategory());
//        product.getCategory().setId(productDto.getId());
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> getSingleProduct(Long id) {
        FullApiResonse res=restTemplate.getForObject(
                "https://fakestoreapi.in/api/products/"+id,
                FullApiResonse.class
        );
        if (res == null || res.getProduct() == null) {
            throw new RuntimeException("Product not found");
        }
        FakeStroreProductServiceDto productDto=res.getProduct();

//        System.out.println("product service "+ productDto );
        return fakeStoreToProduct(productDto);
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        GetAllProductDto res= restTemplate.getForObject(
                "https://fakestoreapi.in/api/products",
                GetAllProductDto.class);
        if (res == null) {
            throw new RuntimeException("Failed to fetch products from Fake Store API");
        }
        List<Product> products=new ArrayList<>();

//        for(FakeStroreProductServiceDto prodRes : res.getProducts()){
//            FakeStroreProductServiceDto productDto=res.getProduct();
//            products.add(fakeStoreToProduct(prodRes));
//        }
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> addNewProduct(Product prod) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        FakeStroreProductServiceDto productDto=new FakeStroreProductServiceDto();
        productDto.setId(productDto.getId());
        productDto.setPrice(prod.getPrice());
        productDto.setDescription(prod.getDescription());
        productDto.setTitle(prod.getTittle());
        productDto.setImage(prod.getImageUrl());
//        productDto.setCategory(prod.getCategory());
        HttpEntity<FakeStroreProductServiceDto> request = new HttpEntity<>(productDto, headers);

        ResponseEntity<Product> response = restTemplate.postForEntity(
                "https://fakestoreapi.in/api/products",
                request,
                Product.class, HttpStatus.CREATED
        );

//        return response.getBody();
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {
        return null;
    }
}
