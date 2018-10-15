package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;


import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive")
public class CategoryServiceTest {

    @Autowired
    private CategoryService realCategoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductService productService;
    @InjectMocks
    private CategoryService stubCategoryService;

    private Category categoryTest;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>(Arrays.asList(new Category(),new Category())));
        when(productService.getAll(any(Category.class))).thenReturn(new ArrayList<Product>(Arrays.asList(new Product(),new Product())));
        when(categoryRepository.findByName(any(String.class))).thenReturn(null);
        categoryTest = new Category();
        categoryTest.setId(0);
        categoryTest.setName("test");
        categoryTest.setDescription("test");
    }

    @Test
    public void getAllCategoryListTest(){
        List<Category> categories = stubCategoryService.getAll();
        assertEquals(2,categories.size());
    }

    @Test
    public void getAllWithCategoryListGetTheirRespectiveProductListsFetchedTest(){
        List<Category> categories = stubCategoryService.getAll();
        assertEquals(2,categories.get(0).getProductList().size());
        assertEquals(2,categories.get(1).getProductList().size());
    }

    @Test
    public void createCategoryTest(){
        when(categoryRepository.save(any(Category.class))).thenReturn(categoryTest);
        assertEquals(categoryTest, stubCategoryService.createCategory(new Category()));
    }

    @Test(expected = BadRequestException.class)
    public void createCategoryWithNonUniqueNameTest(){
        when(categoryRepository.findByName(any(String.class))).thenReturn(categoryTest);
        stubCategoryService.createCategory(new Category());

    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCategoryNotFoundTest(){
        realCategoryService.getCategory(0);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateCategoryNotFoundTest(){
        realCategoryService.updateCategory(categoryTest);
    }
    @Test(expected = ResourceNotFoundException.class)
    public void deleteCategoryNotFoundTest(){
        realCategoryService.deleteCategory(0);
    }

    @Test(expected = ForbiddenException.class)
    public void deleteCategoryWithFirstCategoryBeingProtectedTest(){
        realCategoryService.deleteCategory(1);
    }

    @Test(expected = ForbiddenException.class)
    public void updateCategoryWithFirstCategoryBeingProtectedTest(){
        Category firstCategoryTest;
        firstCategoryTest = new Category();
        firstCategoryTest.setId(1);
        firstCategoryTest.setName("should throw exception");
        realCategoryService.updateCategory(firstCategoryTest);
    }


}