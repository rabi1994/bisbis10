package com.att.tdp.bisbis10.order;

import java.util.UUID;

public class OrderResponse {
    private UUID orderId;

    public OrderResponse() {
    }

    public OrderResponse(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
}
