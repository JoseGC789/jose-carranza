package com.globant.bootcamp.dia15.domain.repository;

import com.globant.bootcamp.dia15.domain.entity.Category;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.publisher = ?1")
    List<Product>findProductsByPublisherUsername(Person publisher);

    List<Product> findByCategories(Category category);
}
