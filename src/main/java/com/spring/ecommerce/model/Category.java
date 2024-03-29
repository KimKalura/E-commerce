package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference("category")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<Product> productList;

    public Category() {
    }

    public Category(Long id, String name, String description, List<Product> productList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Product> getProductList() {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
