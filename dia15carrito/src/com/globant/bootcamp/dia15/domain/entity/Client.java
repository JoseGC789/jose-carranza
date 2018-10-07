package com.globant.bootcamp.dia15.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globant.bootcamp.dia15.domain.ClientRoles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Client implements Serializable {

    //client personal data fields + identification
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ClientRoles role;

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    private String password;
    private String first;
    private String last;
    private Calendar birth;
    private String email;
    //client history fields
    @Column(updatable = false)
    private Calendar dateJoined;
    private Calendar LastSeen;

    @OneToMany
    @JoinColumn(name = "id_client")
    private List<Reservation> reservations = new ArrayList<>();

    public Client() {
    }

    public Client(Integer id, ClientRoles role, String username, String password, String first, String last, Calendar birth, String email, Calendar dateJoined, Calendar lastSeen, List<Reservation> reservations) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.birth = birth;
        this.email = email;
        this.dateJoined = dateJoined;
        LastSeen = lastSeen;
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClientRoles getRole() {
        return role;
    }

    public void setRole(ClientRoles role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Calendar getBirth() {
        return birth;
    }

    public void setBirth(Calendar birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Calendar dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Calendar getLastSeen() {
        return LastSeen;
    }

    public void setLastSeen(Calendar lastSeen) {
        LastSeen = lastSeen;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}