package com.att.tdp.bisbis10.dishes;

import com.att.tdp.bisbis10.restaurant.Restaurant;

import java.io.Serializable;

public class DishId implements Serializable {
    private Restaurant restaurant;
    private Long dishId;

    public DishId() {
    }

    public DishId(Restaurant restaurant, Long dishId) {
        this.restaurant = restaurant;
        this.dishId = dishId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }
}
