package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductRepositoryTestSuite {
    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveRetrieveProduct() {
        //Given
        Product product = new Product(null, "test name", "test description", new BigDecimal(100));

        //When
        productRepository.save(product);
        Long id = product.getId();
        Optional<Product> retrievedTestProduct = productRepository.findById(id);

        //Then
        Assertions.assertTrue(retrievedTestProduct.isPresent());
        Assertions.assertEquals(product.getDescription(), retrievedTestProduct.get().getDescription());

        //CleanUp
        productRepository.deleteById(id);
    }
    @Test
    void testDeleteProduct() {
        //Given
        Product product = new Product(null, "test name to delete", "test description to delete", new BigDecimal(200));
        productRepository.save(product);
        Long id = product.getId();

        //When
        productRepository.deleteById(id);
        Optional<Product> retrievedTestProduct = productRepository.findById(id);

        //Then
        Assertions.assertFalse(retrievedTestProduct.isPresent());

        //CleanUp
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {

        }
    }
    @Test
    void testFindAllProducts() {
        //Given
        Product product1 = new Product(null, "test name1", "test description1", new BigDecimal(100));
        Product product2 = new Product(null, "test name2", "test description2", new BigDecimal(200));
        Product product3 = new Product(null, "test name3", "test description3", new BigDecimal(300));
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        Long id1 = product1.getId();
        Long id2 = product2.getId();
        Long id3 = product3.getId();

        //When
        List<Product> retrievedListOfProducts = productRepository.findAll();

        //Then
        Assertions.assertEquals(3, retrievedListOfProducts.size());

        //CleanUp
        productRepository.deleteById(id1);
        productRepository.deleteById(id2);
        productRepository.deleteById(id3);
    }
}
