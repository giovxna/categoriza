package com.giovxxna.categoriza.services;

import com.giovxxna.categoriza.domain.category.Category;
import com.giovxxna.categoriza.domain.category.CategoryDTO;
import com.giovxxna.categoriza.domain.product.exceptions.ProductNotFoundException;
import com.giovxxna.categoriza.domain.category.exceptions.CategoryNotFoundException;
import com.giovxxna.categoriza.domain.product.Product;
import com.giovxxna.categoriza.domain.product.ProductDTO;
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

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.categoryService.getById(productData.categoryId()).ifPresent(product::setCategory);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());

        this.repository.save(product);
        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);
        this.repository.delete(product);
    }
}
