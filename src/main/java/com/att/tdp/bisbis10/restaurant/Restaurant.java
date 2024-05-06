package com.att.tdp.bisbis10.restaurant;

import com.att.tdp.bisbis10.dishes.Dish;
import com.att.tdp.bisbis10.order.Order;
import com.att.tdp.bisbis10.rating.Rating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class Restaurant {


    @Id
    @SequenceGenerator(
            name = "restaurant_sequence",
            sequenceName = "restaurant_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "restaurant_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonProperty("isKosher")
    private Boolean isKosher;



    @ElementCollection
    private List<String> cuisines;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    private List<Order> orders;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_restaurant_id", referencedColumnName = "id")
    private List<Dish> dishes;


    @Transient
    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Rating rating;

    public Rating getRating() {
        return rating;
    }


    public void setRating(Rating rating) {
        this.rating = rating;
    }
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isKosher() {
        return isKosher;
    }

    public void setKosher(Boolean kosher) {
        kosher = kosher;
    }


    public List<String> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<String> cuisines) {
        this.cuisines = cuisines;
    }

    public  Restaurant()
    {

    }
    public Restaurant(String name, Boolean isKosher, List<String> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isKosher=" + isKosher +
                ", cuisines=" + cuisines +
                '}';
    }
    
}