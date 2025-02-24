package io.michaeljgkopp.github.springconsoledemo.infrastructure.persistence;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.CustomerDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() == null) {
            entityManager.persist(customer);
        } else {
            entityManager.merge(customer);
        }
    }

    @Override
    public void delete(Customer customer) {
        if (customer != null) {
            List<Order> orders = customer.getOrders();
            if (orders != null) {
                orders.forEach(order -> order.setCustomer(null));
            }
            entityManager.remove(customer);
        }
    }
}

