package com.globant.bootcamp.dia15;

import com.globant.bootcamp.dia15.domain.repository.CategoryRepository;
import com.globant.bootcamp.dia15.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main (String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        Category category = new Category();
        category.setName("limpieza");
        categoryRepository.save(category);
        category = new Category();
        category.setName("mpiasdfasd");
        categoryRepository.save(category);
        category = new Category();
        category.setName("mpiasdfasd");
        categoryRepository.save(category);
        Client client = new Client();
        client.setUsername("usertest");
        client.setFirst("firsttest");
        client.setLast("lastetst");
        client.setEmail("emailtest");
        clientRepository.save(client);
        Client asd2 = clientRepository.findByUsername("usertest");
        List<Category> asd = categoryRepository.findAll();
        //System.out.printf("%d",asd.size());
        System.out.printf("\n\n%s\n\n",asd2 != null ? asd2.getUsername():"Not found");*/
    }
}
