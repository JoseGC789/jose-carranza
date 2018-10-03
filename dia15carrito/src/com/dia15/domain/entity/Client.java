package com.dia15.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client implements Serializable {

    //client personal data fields + identification
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ClientRoles role;
    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String first;
    private String last;
    private Date birth;
    private String email;
    //client history fields
    private Date dateJoined;
    private Date dateSeen;
    @OneToMany
    @JoinColumn(name = "id_client")
    private List<Reservation> reservations = new ArrayList<>();

    public Client() {
    }

    public Client(Integer id, ClientRoles role, String username, String password, String first, String last, Date birth, String email, Date dateJoined, Date dateSeen, List<Reservation> reservations) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
        this.first = first;
        this.last = last;
        this.birth = birth;
        this.email = email;
        this.dateJoined = dateJoined;
        this.dateSeen = dateSeen;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getDateSeen() {
        return dateSeen;
    }

    public void setDateSeen(Date dateSeen) {
        this.dateSeen = dateSeen;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}