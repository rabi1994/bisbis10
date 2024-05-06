package com.att.tdp.bisbis10.dishes;

import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants/{restaurantId}/dishes")
public class DishesController {

    final private DishesService dishesService;
    final private RestaurantService restaurantService;

    @Autowired
    public DishesController(DishesService dishesService, RestaurantService restaurantService) {
        this.dishesService = dishesService;
        this.restaurantService = restaurantService;
    }


    @PostMapping
    public ResponseEntity<String> addNewDish(@PathVariable Long restaurantId,
                                             @RequestBody (required = true) Dish dish){
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        dish.setRestaurant(restaurant);
        dishesService.addNewDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body("New dish created successfully");
    }



    @DeleteMapping("{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long restaurantId, @PathVariable Long dishId){
        dishesService.deleteDish(restaurantId, dishId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<Dish> getDishesByRestaurantId(@PathVariable Long restaurantId){
        return dishesService.getDishesByRestaurantId(restaurantId);
    }
    @PutMapping(path = "{dishId}")
    public void updateRestaurantDish(@PathVariable Long restaurantId, @PathVariable Long dishId,
                                     @RequestBody Dish dish){
        dishesService.updateDishDescriptionOrPrice(restaurantId, dishId, dish);
    }

}
