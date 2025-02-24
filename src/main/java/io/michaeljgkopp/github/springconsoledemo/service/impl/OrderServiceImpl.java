package io.michaeljgkopp.github.springconsoledemo.service.impl;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.OrderDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;
import io.michaeljgkopp.github.springconsoledemo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.findById(id);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    @Override
    @Transactional
    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderDAO.findById(id);
        if (order != null) {orderDAO.delete(order);}
    }
}