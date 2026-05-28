package com.capstone.springprojecta.services;

import com.capstone.springprojecta.dto.CategoryResponseDto;
import com.capstone.springprojecta.models.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {

    public List<CategoryResponseDto> getAllCategories();
    public CategoryResponseDto getCategoryById(Long id);
    public ResponseEntity<Category> updateCategory(Long id, Category category);
}
