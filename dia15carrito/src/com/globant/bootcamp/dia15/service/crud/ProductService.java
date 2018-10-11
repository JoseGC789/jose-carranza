package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constants.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ResourceNotFoundException;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PersonService personService;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getAll(Person publisher){
        return productRepository.findProductsByPublisherUsername(publisher);
    }

    public List<Product> getAll(Category category){
        return productRepository.findByCategories(category);
    }

    public Product getProduct(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
    }

    public Product createProduct(Product product){
        checkCategoriesIsNull(product);
        initializeFields(product);
        checkPersonExistence(product.getPublisher());
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));

        checkPersonExistence(product.getPublisher());
        return productRepository.save(product);
    }

    public Product deleteProduct(Integer id){
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
        productRepository.delete(productFromDB);
        return productFromDB;
    }

    private void initializeFields (Product product){
        product.setReservations(new ArrayList<>());
    }

    private void checkPersonExistence(Person person){
        if (person == null){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PUBLISHER_IS_NULL.getString());
        }
        personService.getPerson(person.getId());
        person.setPublishedList(new ArrayList<>());
    }

    private void checkCategoriesIsNull (Product product){
        if (product.getCategories() == null){
            product.setCategories(new ArrayList<>());
        }
    }
}
