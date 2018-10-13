package com.globant.bootcamp.dia15.domain.repository;

import com.globant.bootcamp.dia15.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    Category findByName (String name);

    @Query ("SELECT c FROM Category c WHERE c.name like %?1%")
    List<Category> findByCoincidence(String target);

    @Query ("SELECT c FROM Category c WHERE c.name like ?1%")
    List<Category> findByStartingChar(char target);
}
