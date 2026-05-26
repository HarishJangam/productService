package com.capstone.springprojecta.services;

import com.capstone.springprojecta.dto.CategoryResponseDto;
import com.capstone.springprojecta.exceptions.ProductNotFoundException;
import com.capstone.springprojecta.models.Category;
import com.capstone.springprojecta.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImp implements  CategoryService{
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryImp(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public List<CategoryResponseDto> getAllCategories() {
         List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryResponseDto dto=new CategoryResponseDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).toList();
//        return categories;
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        Optional<Category> category=categoryRepository.findById(id);
        CategoryResponseDto cat=new CategoryResponseDto();
        cat.setId(category.get().getId());
        cat.setName(category.get().getName());
        return cat;
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long id, Category category) {
//        Optional<Category> cat=categoryRepository.findById(id);
//        Category savedCategory=null ;
//        if(!cat.isEmpty() && cat.isPresent()){
//            Category category1=new Category();
//            category1.setId(category.getId());
//            category1.setName(category.getName());
//            savedCategory=categoryRepository.save(category1);
//        }
//        else {
//            throw new ProductNotFoundException("Category with id "+ id +" Not Found");
//        }
//        return new ResponseEntity<>(savedCategory, HttpStatus.OK);

            Category existingCategory = categoryRepository
                    .findById(id)
                    .orElseThrow(() ->
                            new ProductNotFoundException(
                                    "Category with id " + id + " not found"));

            existingCategory.setName(category.getName());

            Category savedCategory =
                    categoryRepository.save(existingCategory);

            return ResponseEntity.ok(savedCategory);
    }

}
