package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceTestSuite {

    @Autowired
    ProductService productService;

    private static Long id1;
    private static Long id2;
    private static Long id3;

    @BeforeEach
    void setData() {
        Product product1 = new Product(
                null,
                "Product 1 name",
                "Product 1 description",
                new BigDecimal(101)
        );
        Product product2 = new Product(
                null,
                "Product 2 name",
                "Product 2 description",
                new BigDecimal(101)
        );
        Product product3 = new Product(
                null,
                "Product 3 name",
                "Product 3 description",
                new BigDecimal(101)
        );
        productService.saveProduct(product1);
        productService.saveProduct(product2);
        productService.saveProduct(product3);
        id1 = product1.getId();
        id2 = product2.getId();
        id3 = product3.getId();
    }
    @AfterEach
    void cleanUp() {
        try {
            productService.deleteById(id1);
            productService.deleteById(id2);
            productService.deleteById(id3);
        } catch (Exception e) {

        }
    }

    @Test
    void testGetAllProducts() {
        //Given & When
        List<Product> retrievedProductList = productService.getAllProducts();

        //Then
        assertEquals(3, retrievedProductList.size());
    }
    @Test
    void testGetProductThatExists() {
        //Given & When
        Product retrievedProduct = productService.getProduct(id1);

        //Then
        assertEquals("Product 1 name", retrievedProduct.getName());
    }
    @Test
    void testGetProductThatNotExists() {
        //Given & When
        Product retrievedProduct = productService.getProduct(id3+1000);

        //Then
        assertEquals(null, retrievedProduct);
    }
    @Test
    void testSaveProduct() {
        //Given
        Product product4 = new Product(
                null,
                "test to save",
                "product desc. testing save method",
                new BigDecimal(230)
        );

        //When
        productService.saveProduct(product4);
        Long id4 = product4.getId();
        Optional retrievedProduct = Optional.ofNullable(productService.getProduct(id4));

        //Then
        assertTrue(retrievedProduct.isPresent());

        //CleanUp
        try {
            productService.deleteById(id4);
        } catch (Exception e) {

        }
    }
    @Test
    void testDeleteById() {
        //Given
        Product product5 = new Product(
                null, "test to delete",
                "product desc. testing delete method",
                new BigDecimal(230)
        );
        productService.saveProduct(product5);
        Long id5 = product5.getId();

        //When
        productService.deleteById(id5);
        Optional retrievedProduct = Optional.ofNullable(productService.getProduct(id5));

        //Then
        assertFalse(retrievedProduct.isPresent());

        //CleanUp
        try {
            productService.deleteById(id5);
        } catch (Exception e) {

        }
    }
}