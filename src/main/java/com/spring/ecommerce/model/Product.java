package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @ManyToOne
    @JsonBackReference("productList")
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItemList;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<WishlistItem> wishlistItems;


    public Product() {
    }

    public Product(Long id, String name, String description, Double price, Category category, List<CartItem> cartItems, List<WishlistItem> wishlistItems) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.cartItems = cartItems;
        this.wishlistItems = wishlistItems;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void setWishlistItems(List<WishlistItem> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }


}
