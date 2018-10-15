package com.globant.bootcamp.dia15.service;

import com.globant.bootcamp.dia15.constant.ExceptionMessages;
import com.globant.bootcamp.dia15.domain.entity.Person;
import com.globant.bootcamp.dia15.domain.entity.Product;
import com.globant.bootcamp.dia15.domain.entity.Reservation;
import com.globant.bootcamp.dia15.exceptions.BadRequestException;
import com.globant.bootcamp.dia15.exceptions.ForbiddenException;
import com.globant.bootcamp.dia15.service.crud.ProductService;
import com.globant.bootcamp.dia15.service.crud.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserReservationService {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ProductService productService;

    public List<Reservation> getUserReservations (Person person){
        return reservationService.getAll(person);
    }

    public List<Reservation> getProductReservations (Person person, int id){
        Product product = productService.getProduct(id);
        checkOwnershipOfProduct(!person.equals(product.getPublisher()));
        return reservationService.getAll(product);
    }

    public Reservation createReservation (Person person, Integer id, int amount){
        Reservation reservation = new Reservation();
        Product product = productService.getProduct(id);
        reservation.setPerson(person);
        reservation.setProduct(product);
        reservation.setQuantity(amount);
        product.setQuantity(subtractProductStock(product.getQuantity(),amount));
        productService.updateProduct(product);
        return reservationService.createReservation(reservation);
    }

    public Reservation closeReservation (Person person,Integer id){
        Reservation reservation = reservationService.getReservation(id);
        checkOwnershipOfReservation(!person.equals(reservation.getPerson()));
        Product product = reservation.getProduct();
        product.setQuantity(product.getQuantity()+reservation.getQuantity());
        productService.updateProduct(product);
        return reservationService.deleteReservation(id);
    }

    public Reservation sellReservation (Person person,Integer id){
        Reservation reservation = reservationService.getReservation(id);
        checkOwnershipOfReservation(!person.equals(reservation.getPerson()));
        return reservationService.deleteReservation(id);
    }

    private void checkOwnershipOfReservation(boolean areDistinct){
        if (areDistinct){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_RESERVATION_IS_NOT_YOURS.getString());
        }
    }

    private void checkOwnershipOfProduct(boolean areDistinct){
        if (areDistinct){
            throw new ForbiddenException(ExceptionMessages.FORBIDDEN_PRODUCT_IS_NOT_YOURS.getString());
        }
    }

    private int subtractProductStock(int stock, int amount){
        if (stock - amount < 0){
            throw new BadRequestException(ExceptionMessages.BAD_REQUEST_PRODUCT_HAS_INSUFFICIENT_STOCK.getString());
        }
        return stock - amount;
    }
}
