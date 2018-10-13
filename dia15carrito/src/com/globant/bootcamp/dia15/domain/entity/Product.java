package com.globant.bootcamp.dia15.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.globant.bootcamp.dia15.constant.ProductState;
import com.globant.bootcamp.dia15.constant.ProductStock;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Product implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, updatable = false)
    private String name;
    @Column(nullable = false)
    @Min(0)
    private int quantity;
    private String description;
    private String imgRef;
    @DecimalMin("1.00")
    private long price;
    @Column(nullable = false)
    private ProductState state;
    @Column(nullable = false)
    private ProductStock status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("publisherList")
    private Person publisher;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservation> reservations;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("productList")
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id")
    )
    private List<Category> categories;

    public Product() {
    }

    public Product(Integer id, String name, @Min(0) int quantity, String description, String imgRef, @DecimalMin("1.00") long price, ProductState state, ProductStock status, Person publisher, List<Reservation> reservations, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.imgRef = imgRef;
        this.price = price;
        this.state = state;
        this.status = status;
        this.publisher = publisher;
        this.reservations = reservations;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public ProductState getState() {
        return state;
    }

    public void setState(ProductState state) {
        this.state = state;
    }

    public ProductStock getStatus() {
        return status;
    }

    public void setStatus(ProductStock status) {
        this.status = status;
    }

    public Person getPublisher() {
        return publisher;
    }

    public void setPublisher(Person publisher) {
        this.publisher = publisher;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}