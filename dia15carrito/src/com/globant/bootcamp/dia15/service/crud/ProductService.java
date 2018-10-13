package com.globant.bootcamp.dia15.service.crud;

import com.globant.bootcamp.dia15.constant.ExceptionMessages;
import com.globant.bootcamp.dia15.constant.ProductStock;
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
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ReservationService reservationService;

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public List<Product> getAll(Person publisher){
        return productRepository.findByPublisher(publisher);
    }

    public List<Product> getAll(Category category){
        return productRepository.findByCategories(category);
    }

    public List<Product> getAll(String name){
        return productRepository.findBySimilarName(name);
    }

    public Product getProduct(Integer id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
    }

    public Product createProduct(Product product){
        checkCategoriesIsNull(product);
        initializeFields(product);
        checkPublisherExistence(product.getPublisher());
        uncategorizeProduct(product);
        setProductStockState(product);
        return productRepository.save(product);
    }

    public Product updateProduct(Product product){
        productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
        checkCategoriesIsNull(product);
        checkPublisherExistence(product.getPublisher());
        uncategorizeProduct(product);
        setProductStockState(product);
        return productRepository.save(product);
    }

    public Product deleteProduct(Integer id){
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionMessages.NOT_FOUND_PRODUCT.getString()));
        protectProductWithReservationPending(productFromDB);
        productRepository.delete(productFromDB);
        return productFromDB;
    }

    private void protectProductWithReservationPending (Product product){
        if (!reservationService.getAll(product).isEmpty()){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PRODUCT_HAS_RESERVATION_PENDING.getString());
        }
    }

    private void uncategorizeProduct(Product product){
        if (product.getCategories().isEmpty()){
            product.getCategories().add(categoryService.getCategory(1));
        }
    }

    private void initializeFields (Product product){
        product.setReservations(new ArrayList<>());
    }

    private void checkPublisherExistence(Person person){
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

    private void setProductStockState (Product product){
        if (product.getQuantity()>0){
            product.setStatus(ProductStock.AVAILABLE);
        }else{
            product.setStatus(ProductStock.UNAVAILABLE);
        }
    }
}
