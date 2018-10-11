package com.globant.bootcamp.dia15.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false, unique = true, updatable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Product> products;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties("categories")
    @Transient
    private List<Product> productList;

    public Category() {
    }

    public Category(Integer id, String name, String description, List<Product> products, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
        this.productList = productList;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

