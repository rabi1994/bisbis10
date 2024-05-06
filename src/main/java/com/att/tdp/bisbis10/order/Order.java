package com.att.tdp.bisbis10.order;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private UUID orderId;
    @Transient
    @JsonProperty("restaurantId")
    private Long tempRestaurantId;
    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id")
    private Restaurant restaurant;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_order_id", referencedColumnName = "orderId")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Restaurant restaurant, List<OrderItem> orderItems) {
        this.restaurant = restaurant;
        this.orderItems = orderItems;
    }

    public Order(Long tempRestaurantId, Restaurant restaurant, List<OrderItem> orderItems) {
        this.tempRestaurantId = tempRestaurantId;
        this.restaurant = restaurant;
        this.orderItems = orderItems;
    }

    public Long getTempRestaurantId() {
        return tempRestaurantId;
    }

    public void setTempRestaurantId(Long tempRestaurantId) {
        this.tempRestaurantId = tempRestaurantId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public UUID getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", restaurant=" + restaurant +
                ", orderItems=" + orderItems +
                '}';
    }
}
