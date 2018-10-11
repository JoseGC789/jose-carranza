package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductService productService;

    public List<Category> getAll(){
        List<Category> categoryList = categoryRepository.findAll();
        for (Category category:categoryList) {
            setProductList(category);
        }

        return categoryList;
    }

    public Category getCategory(Integer id){
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY.getString()));
        setProductList(category);
        return category;
    }

    public Category createCategory(Category category){
        category.setProductList(new ArrayList<>());
        category.setProducts(new ArrayList<>());
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

    private void setProductList (Category category){
        //category.setProductList(productService.getAll(category));
    }
}
