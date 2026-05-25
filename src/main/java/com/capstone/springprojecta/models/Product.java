package com.capstone.springprojecta.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JsonBackReference
    private Category category;
    private String description;
    private String imageUrl;
}
