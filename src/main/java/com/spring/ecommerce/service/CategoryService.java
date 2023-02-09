package com.spring.ecommerce.service;

import com.spring.ecommerce.model.Category;
import com.spring.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;


    public CategoryService(@Autowired CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category category) {
        //1. exista categoria deja in DB?
        //2.1 daca nu exista o salvam
        //2.2 daca exista aruncam o exceptie
       // cum afla ca o categorie daca este sau nu in DB:-> findByName
        Category foundCategory = categoryRepository.findByName(category.getName());
        if (foundCategory == null) {
            return categoryRepository.save(category);
        } else {
            throw new ResponseStatusException(HttpStatus.CREATED, "category already exists");
        }
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category updateCategory(Category category, Long categoryId) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "the category you want to update was not found"));
        foundCategory.setDescription(category.getDescription());
        foundCategory.setName(category.getName());
        return categoryRepository.save(foundCategory);
    }

}

