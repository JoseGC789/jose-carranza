package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constants.ExceptionMessages;
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

    public List<Product> getProductRepository(){
        return productRepository.findAll();
    }

    public Product getProduct(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
    }

    public Product createProduct(Product product){
        product.setReservations(new ArrayList<>());
        product.setCategories(new ArrayList<>());

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
        productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
        Product productFromDB = productRepository.getOne(id);
        productRepository.delete(productFromDB);
        return productFromDB;
    }

    public List<Product> getProductByPublisher (Person publisher){
        return productRepository.findProductsByPublisherUsername(publisher);
    }

    private void checkPersonExistence(Person person){
        if (person == null){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PUBLISHER_IS_NULL.getString());
        }
        personService.getPerson(person.getId());
        person.setPublishedList(new ArrayList<>());
    }
}
