package com.dia15;

import com.dia15.domain.entity.Category;
import com.dia15.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main (String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category();
        category.setName("limpieza");
        categoryRepository.save(category);
        category = new Category();
        category.setName("qimpiasdfasd");
        categoryRepository.save(category);
        List<Category> asd = categoryRepository.findByCoincidence("mpi");
        //System.out.printf("%d",asd.size());
        System.out.printf("\n\n%s\n\n",asd.size()>0 ? asd.get(1).getName():"Not found");
    }
}
