package com.globant.bootcamp.dia15.domain.repository;

import com.globant.bootcamp.dia15.domain.entity.Client;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    Reservation findByClient(Client client);

}
