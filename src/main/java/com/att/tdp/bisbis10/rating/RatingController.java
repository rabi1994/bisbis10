package com.att.tdp.bisbis10.rating;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/ratings")

public class RatingController
{
    private final RatingService ratingService;
    private final RestaurantService restaurantService;
    @Autowired
    public RatingController(RatingService ratingService,RestaurantService restaurantService) {
        this.ratingService = ratingService;
        this.restaurantService = restaurantService;
    }
    @PostMapping("/add")
    public ResponseEntity addRating(@RequestBody Rating rating) {
        ratingService.addRating(rating);
        return new ResponseEntity(HttpStatus.ACCEPTED);
        }
}

