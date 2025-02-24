package io.michaeljgkopp.github.springconsoledemo.service.impl;

import io.michaeljgkopp.github.springconsoledemo.domain.dao.ProductDAO;
import io.michaeljgkopp.github.springconsoledemo.domain.entity.Product;
import io.michaeljgkopp.github.springconsoledemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        productDAO.delete(product);
    }
}
