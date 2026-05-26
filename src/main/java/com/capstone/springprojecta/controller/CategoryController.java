package com.capstone.springprojecta.controller;

import com.capstone.springprojecta.dto.CategoryResponseDto;
import com.capstone.springprojecta.models.Category;
import com.capstone.springprojecta.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping()
    public List<CategoryResponseDto> getAllCategory(){
        return categoryService.getAllCategories();
    }
    @GetMapping("{id}")
    public CategoryResponseDto getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Long id,
            @RequestBody Category category) {

        return categoryService.updateCategory(id, category);
    }
}
