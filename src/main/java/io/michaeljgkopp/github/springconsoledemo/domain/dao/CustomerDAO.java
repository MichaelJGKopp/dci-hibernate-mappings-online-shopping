package io.michaeljgkopp.github.springconsoledemo.domain.dao;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    Customer findById(Long id);

    List<Customer> findAll();

    void save(Customer customer);

    void delete(Customer customer);
}