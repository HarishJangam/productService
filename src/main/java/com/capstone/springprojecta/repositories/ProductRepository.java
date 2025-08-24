package com.capstone.springprojecta.repositories;

import com.capstone.springprojecta.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository
        extends JpaRepository<Product,Long> {
}
