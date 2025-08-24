package com.capstone.springprojecta.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStroreProductServiceDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
