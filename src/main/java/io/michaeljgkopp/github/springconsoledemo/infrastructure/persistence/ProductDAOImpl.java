package io.michaeljgkopp.github.springconsoledemo.infrastructure.persistence;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.ProductDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Order;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    public void delete(Product product) {
        if (product != null) {
            List<Order> orders = product.getOrders();
            if (orders != null) {
                orders.forEach(order -> order.getProducts().remove(product));
            }
            entityManager.remove(product);
        }
    }
}