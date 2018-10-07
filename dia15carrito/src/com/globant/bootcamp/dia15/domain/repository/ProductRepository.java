package com.globant.bootcamp.dia15.domain.repository;

import com.globant.bootcamp.dia15.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByName(String name);
}
