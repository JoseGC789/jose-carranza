package com.globant.bootcamp.dia15.controller.crud;

import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import com.globant.bootcamp.dia15.service.SecurityEndpointService;
import com.globant.bootcamp.dia15.service.crud.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SecurityEndpointService securityEndpointService;
    private List<PersonRoles> requiredRoles = Arrays.asList(PersonRoles.ADMIN, PersonRoles.SUPER);

    //get all
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(@RequestHeader("Authorization") String token){
        validateRequest(token);
        return ResponseEntity.ok().body(categoryService.getAll());
    }
    //get one
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@RequestHeader("Authorization") String token, @PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }
    //create
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestHeader("Authorization") String token,@RequestBody Category category){
        validateRequest(token);
        return ResponseEntity.ok().body(categoryService.createCategory(category));
    }
    //update
    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestHeader("Authorization") String token,@RequestBody Category category){
        validateRequest(token);
        return ResponseEntity.ok().body(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@RequestHeader("Authorization") String token,@PathVariable Integer id){
        validateRequest(token);
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

    private void validateRequest(String token){
        securityEndpointService.validateRequest(token, requiredRoles);
    }

}
