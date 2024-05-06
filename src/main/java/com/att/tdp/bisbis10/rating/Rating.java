package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ratingId;

    @OneToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    private Float rating;

    public long getRatingId() {
        return ratingId;
    }

    public void setRatingId(long ratingId) {
        this.ratingId = ratingId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }


    public Rating()
    {
    }
    public Rating(Restaurant restaurant,Float rating)
    {
        this.restaurant = restaurant;
        this.rating = rating;
    }




}
