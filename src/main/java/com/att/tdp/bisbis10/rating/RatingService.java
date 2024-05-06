package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private RestaurantRepository restaurantRepository;
    @Autowired
    public RatingService(RatingRepository ratingRepository,RestaurantRepository restaurantRepository)
    {
        this.ratingRepository = ratingRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addRating(Rating rating){
        Optional<Restaurant> restaurant = restaurantRepository.findById(rating.getTempRestaurantId());
        if (restaurant.isEmpty()){
            throw new IllegalStateException("restaurant not found!");
        }
        rating.setRestaurant(restaurant.get());
        ratingRepository.save(rating);
    }

}
