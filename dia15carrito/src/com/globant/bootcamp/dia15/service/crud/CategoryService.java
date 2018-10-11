package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategoryRepository(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
    }

    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category){
        categoryRepository.findById(category.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Integer id){
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        Category categoryFromDB = categoryRepository.getOne(id);
        categoryRepository.delete(categoryFromDB);
        return categoryFromDB;
    }
}
