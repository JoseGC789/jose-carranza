package com.dia15.domain.repository;

import com.dia15.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByUsername(String username);

    Client findByFirst(String first);

    Client findByLast(String last);

    Client findByEmail(String email);

}
