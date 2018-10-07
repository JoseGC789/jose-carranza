package com.globant.bootcamp.dia15.service;


import com.globant.bootcamp.dia15.NotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categories;

    public List<Category> getCategories(){
        return categories.findAll();
    }

    public Category getCategory(Integer id){
        categories.findById(id)
                .orElseThrow(() -> new NotFoundException());
        return categories.getOne(id);

    }

    public Category createCategory(Category category){
        return categories.save(category);
    }

    public Category updateCategory(Category category){
        categories.findById(category.getId())
                .orElseThrow(() -> new NotFoundException());
        return categories.save(category);
    }

    public Category deleteCategory(Integer id){
        categories.findById(id)
                .orElseThrow(() -> new NotFoundException());
        Category categoryFromDB = categories.getOne(id);
        categories.delete(categoryFromDB);
        return categoryFromDB;
    }
}
