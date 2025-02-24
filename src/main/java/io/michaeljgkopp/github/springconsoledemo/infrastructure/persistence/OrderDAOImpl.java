package io.michaeljgkopp.github.springconsoledemo.infrastructure.persistence;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.OrderDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Customer;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findById(Long id) {
        return entityManager.createQuery(
                "SELECT o FROM Order o LEFT JOIN FETCH o.products WHERE o.id=:id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
//        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o LEFT JOIN FETCH o.products", Order.class)
                .getResultList();
    }

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
    }

    @Override
    public void delete(Order order) {
        if (order != null) {
            Customer customer = order.getCustomer();
            if (customer != null) {
                List<Order> orders = customer.getOrders();
                if (orders != null) {
                    orders.remove(order);
                }
            }
            order.setCustomer(null);

            List<Product> products = order.getProducts();
            if (products != null) {
                products.forEach(product -> {
                    List<Order> orders = product.getOrders();
                    if (orders != null) {
                        orders.remove(order);
                    }
                });
            }

            entityManager.remove(order);
        }
    }
}