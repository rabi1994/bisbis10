package com.att.tdp.bisbis10.restaurant;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;


@Service
public class RestaurantService {

    @Autowired
    final private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }
    public void saveRestaurant(Restaurant restaurant) {
         restaurantRepository.save(restaurant);

    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantByCuisine(String cuisine)
    {
        Optional<List<Restaurant>> restaurants = restaurantRepository.findRestaurantsByCuisines(cuisine);
        if (restaurants.isEmpty()){
            throw new IllegalStateException("no restaurants found!");
        }
        return restaurants.get() ;
    }
    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }


    @Transactional
    public void updateRestaurant(Long restaurantId, Restaurant restaurant) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isEmpty()){
            throw new IllegalStateException("no restaurants found!");
        }
        Restaurant originalRestaurant = optionalRestaurant.get();
        if (restaurant.getName() != null & !Objects.equals(originalRestaurant.getName(), restaurant.getName())){
            originalRestaurant.setName(restaurant.getName());
        }
        if (restaurant.isKosher() != null & !Objects.equals(originalRestaurant.isKosher(),restaurant.isKosher())){
            originalRestaurant.setKosher(restaurant.isKosher());
        }
        if (restaurant.getCuisines() != null & !Objects.equals(originalRestaurant.getCuisines(),restaurant.getCuisines())){
            originalRestaurant.setCuisines(restaurant.getCuisines());
        }
        restaurantRepository.save(originalRestaurant);
    }

    public void deleteRestaurant(Long restaurantId) {
        if (!restaurantRepository.existsById(restaurantId)){
            throw new IllegalStateException("no restaurants found!");
        }
        restaurantRepository.deleteById(restaurantId);
    }

}