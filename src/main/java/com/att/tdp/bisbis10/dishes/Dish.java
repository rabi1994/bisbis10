package com.att.tdp.bisbis10.dishes;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
@IdClass(DishId.class)
public class Dish {

    private String name;
    private String description;
    private Integer price;
    @Id
    @SequenceGenerator(
            name = "dish_sequence",
            sequenceName = "dish_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "dish_sequence"
    )
    @JsonProperty("id")
    private Long dishId;
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_restaurant_id")
    @JsonIgnore
    private Restaurant restaurant;


    public Dish() {
    }

    public Dish(Long dishId, String name, String description, Integer price) {
        this.dishId = dishId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Dish(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getDishId() {
        return dishId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
