package com.capstone.springprojecta.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    @Id here we are using inheritance for that we are using mapped super class
//    private long id;
    private String tittle;
    private double price;
    @ManyToOne
    private Category category;
    private String description;
    private String imageUrl;
}
