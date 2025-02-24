package io.michaeljgkopp.github.springconsoledemo.service.impl;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.CustomerDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;
import io.michaeljgkopp.github.springconsoledemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer customer = customerDAO.findById(id);
        if (customer != null) {
            customerDAO.delete(customer);
        }
    }
}