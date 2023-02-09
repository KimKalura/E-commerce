package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class WishlistItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name= "wishlist_id")
    private Wishlist wishlist;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="product_id")
    private Product product;



    public WishlistItem(){}

    public WishlistItem(Long id, User user, Wishlist wishlist) {
        this.id = id;
        this.wishlist = wishlist;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
