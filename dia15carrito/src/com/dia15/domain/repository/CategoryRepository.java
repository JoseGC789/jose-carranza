package com.dia15.domain.repository;

import com.dia15.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByNameAndDescription(String name, String description);

    @Query ("SELECT c FROM Category c WHERE c.name like %?1%")
    List<Category> findByCoincidence(String coincidence);
}
