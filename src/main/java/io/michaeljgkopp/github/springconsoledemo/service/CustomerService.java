package io.michaeljgkopp.github.springconsoledemo.service;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);

    List<Customer> getAllCustomers();

    void saveCustomer(Customer customer);

    void deleteCustomer(Long id);
}
