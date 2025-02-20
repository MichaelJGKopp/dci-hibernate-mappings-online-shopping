package io.michaeljgkopp.github.springconsoledemo.domain.dao;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;

import java.util.List;

public interface OrderDAO {
    Order findById(Long id);

    List<Order> findAll();

    void save(Order order);

    void delete(Long id);
}