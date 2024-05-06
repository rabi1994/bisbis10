package com.att.tdp.bisbis10.order;

import com.att.tdp.bisbis10.dishes.DishesService;
import com.att.tdp.bisbis10.restaurant.Restaurant;
import com.att.tdp.bisbis10.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    final private OrderRepository orderRepository;
    final private RestaurantService restaurantService;
    final private DishesService dishesService;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantService restaurantService, DishesService dishesService) {
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.dishesService = dishesService;
    }

    public List<Order> getOrdersByRestaurantId(Long restaurantId) {
        Optional<List<Order>> orderOptional = orderRepository.findOrdersByRestaurantId(restaurantId);
        if (orderOptional.isEmpty()){
            throw new IllegalStateException("no order found for this restaurant");
        }
        return orderOptional.get();
    }
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }





    public void addNewOrder(Order order) {
        Restaurant restaurant = restaurantService.getRestaurantById(order.getTempRestaurantId());
        order.setRestaurant(restaurant);
        for (OrderItem orderItem: order.getOrderItems()){
            orderItem.setDish(dishesService.getDishByRestaurantIdAndDishId(order.getRestaurant().getId(), orderItem.getTempDishId()));
        }
        orderRepository.save(order);
    }
}
