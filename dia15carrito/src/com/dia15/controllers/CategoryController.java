package com.dia15.controllers;

import com.dia15.domain.entity.Category;
import com.dia15.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categories;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categories.findAll());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){
        Category categoryFromDB = categories.getOne(id);
        if (categoryFromDB != null){
            return ResponseEntity.ok().body(categoryFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        Category categorySaved = categories.save(category);
        return ResponseEntity.ok().body(categorySaved);
    }

    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category categoryFromDB = categories.getOne(category.getId());
        if (categoryFromDB != null){
            Category categorySaved = categories.save(category);
            return ResponseEntity.ok().body(categorySaved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
        Category categoryFromDB = categories.getOne(id);
        if (categoryFromDB != null){
            categories.delete(categoryFromDB);
            return ResponseEntity.ok().body(categoryFromDB);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
