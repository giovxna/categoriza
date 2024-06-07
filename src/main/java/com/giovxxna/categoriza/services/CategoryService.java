package com.giovxxna.categoriza.services;

import com.giovxxna.categoriza.domain.category.Category;
import com.giovxxna.categoriza.domain.category.CategoryDTO;
import com.giovxxna.categoriza.repositories.CategoryRepository;
import com.giovxxna.categoriza.domain.category.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return newCategory;
    }
}
