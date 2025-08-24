package com.capstone.springprojecta;

import com.capstone.springprojecta.dto.GetAllProductDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class SpringProjectAApplication {

    public static void main(String[] args) {
//        GetAllProductDto dto = new GetAllProductDto();
//        dto.setProducts(new ArrayList<>()); // If this works, Lombok is fine
//        System.out.println(dto.getProducts());
        SpringApplication.run(SpringProjectAApplication.class, args);
    }

}
