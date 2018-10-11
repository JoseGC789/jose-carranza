package com.globant.bootcamp.dia15.service.crud;


import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
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
        checkNameUniqueness(category.getName());
        category.setProductList(new ArrayList<>());
        checkProductsIsNull(category);
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
        category.setProductList(productService.getAll(category));
    }

    private void checkProductsIsNull(Category category){
        if (category.getProducts() == null){
            category.setProducts(new ArrayList<>());
        }
    }

    private void checkNameUniqueness(String name){
        if (categoryRepository.findByName(name) != null){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_CATEGORY_NAME_MUST_BE_UNIQUE.getString());
        }
    }
}
