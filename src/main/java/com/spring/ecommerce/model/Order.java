package com.spring.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date createdDate;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<OrderItem> orderItems;

    public Order(){};

    public Order(Long id, Date createdDate, Double totalPrice, User user, List<OrderItem> orderItems) {
        this.id = id;
        this.createdDate = createdDate;
        this.totalPrice = totalPrice;
        this.user = user;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        if (this.orderItems == null){
            this.orderItems = new ArrayList<>();
        }
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
