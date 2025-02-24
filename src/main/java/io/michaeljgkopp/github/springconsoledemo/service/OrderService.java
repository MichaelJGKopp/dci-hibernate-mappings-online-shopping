package io.michaeljgkopp.github.springconsoledemo.service;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;

import java.util.List;

public interface OrderService {
    Order getOrderById(Long id);

    List<Order> getAllOrders();

    void saveOrder(Order order);

    void deleteOrder(Order order);
}
