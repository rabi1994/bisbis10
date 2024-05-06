package com.att.tdp.bisbis10.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity createRestaurant(@RequestBody Restaurant restaurant) {

        restaurantService.saveRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.CREATED).body("New restaurant created successfully");
    }

    @PutMapping(path = "{restaurantId}")
    public void updateRestaurant(@PathVariable("restaurantId") Long restaurantId,
                                 @RequestBody(required= true) Restaurant restaurant){
        restaurantService.updateRestaurant(restaurantId, restaurant);
    }
    @GetMapping
    public List<Restaurant> getAllRestaurants(@RequestParam(required = false) String cuisine) {
        if(cuisine != null)
            return restaurantService.getRestaurantByCuisine(cuisine);
        return restaurantService.getAllRestaurants();
    }



    @DeleteMapping(path = "{restaurantId}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

}