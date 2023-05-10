package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product getProduct(Long id) {
        return repository.findById(id).orElse(null);
    }
    public Product saveProduct(final Product product) {
        return repository.save(product);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}