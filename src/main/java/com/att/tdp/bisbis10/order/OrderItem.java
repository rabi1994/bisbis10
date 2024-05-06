package com.att.tdp.bisbis10.order;


import com.att.tdp.bisbis10.dishes.Dish;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long orderItemId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "fk_restaurant_id"), @JoinColumn(name = "fk_dish_id")
    })
    private Dish dish;
    @Transient
    @JsonProperty("dishId")
    private Long tempDishId;
    private Integer amount;

    public OrderItem() {
    }

    public OrderItem(Dish dish, Integer amount, Long tempDishId) {
        this.dish = dish;
        this.amount = amount;
        this.tempDishId = tempDishId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getTempDishId() {
        return tempDishId;
    }

    public void setTempDishId(Long tempDishId) {
        this.tempDishId = tempDishId;
    }
}
