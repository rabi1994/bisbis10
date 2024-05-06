package com.att.tdp.bisbis10.dishes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DishesService {

    final private DishesRepository dishesRepository;

    @Autowired
    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    public List<Dish> getDishesByRestaurantId(Long restaurantId) {
        Optional<List<Dish>> dishes = dishesRepository.findDishesByRestaurantId(restaurantId);
        if (dishes.isEmpty()){
            throw new IllegalStateException("Restaurant dose not have any dishes");
        }
        return dishesRepository.findDishesByRestaurantId(restaurantId).get();
    }

    public void addNewDish(Dish dishes) {
        dishesRepository.save(dishes);
    }
    public Dish getDishByRestaurantIdAndDishId(Long restaurantId, Long dishId){
        Optional<Dish> dishesOptional = dishesRepository.findDishesByRestaurantIdAndDishId(restaurantId, dishId);
        if (dishesOptional.isEmpty()){
            throw new IllegalStateException("Dish dose not exist in this restaurant");
        }
        return dishesOptional.get();
    }
    public void updateDishDescriptionOrPrice(Long restaurantId, Long dishId, Dish dish) {
        Optional<Dish> dishesOptional = dishesRepository.findDishesByRestaurantIdAndDishId(restaurantId, dishId);
        if (dishesOptional.isEmpty()){
            throw new IllegalStateException("Dish dose not exist in this restaurant");
        }
        Dish originalDish = dishesOptional.get();
        if (dish.getName() != null & !Objects.equals(originalDish.getName(), dish.getName())){
            originalDish.setName(dish.getName());
        }
        if (dish.getDescription() != null & !Objects.equals(originalDish.getDescription(), dish.getDescription())){
            originalDish.setDescription(dish.getDescription());
        }
        if (dish.getPrice() != null & !Objects.equals(originalDish.getPrice(), dish.getPrice())){
            originalDish.setPrice(dish.getPrice());
        }
        dishesRepository.save(originalDish);
    }

    public void deleteDish(Long restaurantId, Long dishId) {
        Optional<Dish> dish = dishesRepository.findDishesByRestaurantIdAndDishId(restaurantId, dishId);
        if (dish.isEmpty()){
            throw new IllegalStateException("Dish dose not exist in this restaurant");
        }
        dishesRepository.delete(dish.get());
    }
}
