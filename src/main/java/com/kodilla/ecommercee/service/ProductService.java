package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    public Product getProduct(final Long id) throws ProductNotFoundException {
        return repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
    public Product saveProduct(final Product product) {
        return repository.save(product);
    }
    public void deleteById(Long id) throws ProductNotFoundException {
        checkIfProductExists(id);
        repository.deleteById(id);
    }
    public void checkIfProductExists(Long id) throws ProductNotFoundException {
        repository.findById(id).orElseThrow(ProductNotFoundException::new);
    }
}