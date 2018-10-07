package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //get all
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }
    //get one
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }
    //create
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.createCategory(category));
    }
    //update
    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Integer id){
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

}
