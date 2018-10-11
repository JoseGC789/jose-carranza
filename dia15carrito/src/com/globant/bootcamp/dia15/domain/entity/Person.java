package com.globant.bootcamp.dia15.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.globant.bootcamp.dia15.constants.PersonRoles;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Person implements Serializable {

    //person personal data fields + identification
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private PersonRoles role;

    @NaturalId
    @Column(unique = true, nullable = false, updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private String first;
    private String last;
    private Calendar birth;
    private String email;
    //person history fields
    @Column(nullable = false, updatable = false)
    private Calendar dateJoined;
    @Column(nullable = false)
    private Calendar LastSeen;

    @OneToMany(mappedBy="person")
    @JsonBackReference
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "publisher")
    @JsonBackReference("published")
    private List<Product> published;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties("publisher")
    @Transient
    private List<Product> publishedList;

    public Person() {
    }

    public Person(Integer id, PersonRoles role, String username, String password, String first, String last, Calendar birth, String email, Calendar dateJoined, Calendar lastSeen, List<Reservation> reservations, List<Product> published, List<Product> publishedList) {
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
        this.published = published;
        this.publishedList = publishedList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonRoles getRole() {
        return role;
    }

    public void setRole(PersonRoles role) {
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

    public List<Product> getPublished() {
        return published;
    }

    public void setPublished(List<Product> published) {
        this.published = published;
    }

    public List<Product> getPublishedList() {
        return publishedList;
    }

    public void setPublishedList(List<Product> publishedList) {
        this.publishedList = publishedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}