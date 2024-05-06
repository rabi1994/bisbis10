package com.att.tdp.bisbis10.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    final private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders(@RequestParam(required = false) Long restaurantId){
        if (restaurantId != null){
            return orderService.getOrdersByRestaurantId(restaurantId);
        }
        return orderService.getAllOrders();
    }

    @PostMapping
    public OrderResponse addNewOrder(@RequestBody Order order){
        orderService.addNewOrder(order);
        return new OrderResponse(order.getOrderId());

    }
}
