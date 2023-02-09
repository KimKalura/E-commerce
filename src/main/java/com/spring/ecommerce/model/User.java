package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    List<CartItem> cartItems;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Wishlist wishlist;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;

    @ManyToMany
    @JsonIgnoreProperties("userList")
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name ="role_id")
    )
    private List<Role> roleList;

    @Column
    private String password;

    public User(){}

    public User(Long id, String username, List<CartItem> cartItems, Wishlist wishlist, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.cartItems = cartItems;
        this.wishlist = wishlist;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Wishlist getWishlist() {
        if(this.wishlist == null){
            wishlist = new Wishlist();
        }
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Role> getRoleList() {
        if (roleList == null){
            roleList = new ArrayList<>();
        }
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


