package com.dia15.domain.repository;

import com.dia15.domain.entity.Product;
import com.dia15.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findByProduct(Product product);

}
