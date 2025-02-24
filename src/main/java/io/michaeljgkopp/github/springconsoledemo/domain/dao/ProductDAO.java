package io.michaeljgkopp.github.springconsoledemo.domain.dao;

import io.michaeljgkopp.github.springconsoledemo.domain.entity.Product;

import java.util.List;

public interface ProductDAO {
    Product findById(Long id);

    List<Product> findAll();

    void save(Product product);

    void delete(Product product);
}