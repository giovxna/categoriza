package com.giovxxna.categoriza.services;

import com.giovxxna.categoriza.domain.category.Category;
import com.giovxxna.categoriza.domain.category.CategoryDTO;
import com.giovxxna.categoriza.domain.category.exceptions.CategoryNotFoundException;
import com.giovxxna.categoriza.domain.products.Product;
import com.giovxxna.categoriza.domain.products.ProductDTO;
import com.giovxxna.categoriza.repositories.CategoryRepository;
import com.giovxxna.categoriza.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private CategoryService categoryService;
    private ProductRepository repository;

    public ProductService(CategoryService categoryService, ProductRepository productRepository){
        this.categoryService = categoryService;
        this.repository = productRepository;
    }

    public Product insert(ProductDTO productData){
        Category category = this.categoryService.getById(productData.categoryId()).orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }

    public Product update(String id, CategoryDTO categoryData){
        Product product = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        this.repository.save(category);
        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }
}
