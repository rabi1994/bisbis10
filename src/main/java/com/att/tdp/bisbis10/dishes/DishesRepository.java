package com.att.tdp.bisbis10.dishes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishesRepository extends JpaRepository<Dish, Long> {

    Optional<List<Dish>> findDishesByRestaurantId(Long restaurantId);
    Optional<Dish> findDishesByRestaurantIdAndDishId(Long restaurantId, Long dishId);
}
